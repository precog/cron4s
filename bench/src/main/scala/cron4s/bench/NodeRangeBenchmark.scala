package cron4s.bench

import cron4s.{CronField, CronUnit}
import cron4s.expr._

import org.openjdk.jmh.annotations._

import shapeless.Coproduct

import scalaz.NonEmptyList

/**
  * Sample run
  *    sbt "bench/jmh:run -r 2 -i 20 -w 2 -wi 20 -f 1 -t 1 cron4s.bench.NodeRangeBenchmark"
  *
  * Which means "20 iterations" of "2 seconds" each, "20 warm-up
  * iterations" of "2 seconds" each, "1 fork", "1 thread".  Please note
  * that benchmarks should be usually executed at least in 10
  * iterations (as a rule of thumb), but the more is better.
  */
@State(Scope.Benchmark)
class NodeRangeBenchmark {
  import CronField._
  import CronUnit._

  val eachNode = EachNode[Minute]
  val constNode = ConstNode[Minute](30)

  val betweenNode: BetweenNode[Minute] = BetweenNode(
    ConstNode(CronUnit.Minutes.min),
    ConstNode(CronUnit.Minutes.max)
  )

  // SeveralNode made of constants and ranges, we ensure the arrays have the same number of elements

  val severalConstNode: SeveralNode[Minute] = {
    val stepSize = Minutes.max / 10
    val minutes = (Minutes.min to Minutes.max by stepSize)
      .map(value => ConstNode[Minute](value))
      .map(Coproduct[EnumerableNode[Minute]](_))

    SeveralNode(NonEmptyList(minutes.head, minutes.tail: _*))
  }

  val severalBetweenNode: SeveralNode[Minute] = {
    val unit = CronUnit[CronField.Minute]
    val chunkSize = unit.max / 10

    val minuteRanges = (unit.min to unit.max by chunkSize).map { lower =>
      BetweenNode[Minute](ConstNode(lower), ConstNode(lower + chunkSize - 1))
    }.map(Coproduct[EnumerableNode[Minute]](_))

    SeveralNode[Minute](NonEmptyList(minuteRanges.head, minuteRanges.tail: _*))
  }

  val everyEachNode = EveryNode(eachNode, 10)
  val everySeveralConstNode = EveryNode(severalConstNode, 10)
  val everySeveralBetweenNode = EveryNode(severalBetweenNode, 10)

  @Benchmark
  def eachNodeRange() = eachNode.range

  @Benchmark
  def constNodeRange() = constNode.range

  @Benchmark
  def betweenNodeRange() = betweenNode.range

  @Benchmark
  def severalConstNodeRange() = severalConstNode.range

  @Benchmark
  def severalBetweenNodeRange() = severalBetweenNode.range

  @Benchmark
  def everyEachNodeRange() = everyEachNode.range

  @Benchmark
  def everySeveralConstNodeRange() = everySeveralConstNode.range

  @Benchmark
  def everySeveralBetweenNodeRange() = everySeveralBetweenNode.range

}
