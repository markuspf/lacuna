
import scala.util.parsing.combinator.RegexParsers
import scala.util.parsing.input.Positional

abstract trait Statement extends Positional
case class Block(statements : List[Statement]) extends Statement
case class ForLoop(variable: String, enumerator: String, statement:Statement) extends Statement

class LoopParser extends RegexParsers {
  override type Elem = Char
  def identifier  = """[_\p{L}][_\p{L}\p{Nd}]*""".r
  def integer     = """(0|[1-9]\d*)""".r ^^ { _.toInt }
  def loop =
    ("for" ~> identifier) ~ ("in" ~> identifier) ~ statement ^^
    { case variable ~ enumerator ~ statement => ForLoop(variable, enumerator, statement) }
  def statements = statement*
  def doblock = "do"~>statements<~"od"  ^^ { l => Block(l) }
  def statement : Parser[Statement] = loop | doblock
}



object TestLoopParser extends LoopParser with App {
  parseAll(loop, "for x in blabla do od") match {
    case Success(lup, _) => println(lup)
    case x => println(x)
  }
}

/**
parseAll(loop, new CharArrayReader("for x in 1 to 42 { for y in 0 to 1 {} }".toArray)) match {
    case Success(lup,_) => {
      println(lup)
      val p = lup.pos
      println(p)
      }
    case x => println(x)
    */