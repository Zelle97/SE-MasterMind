object MasterMind {


  def gamelayout(): String = {
    val title = "\t\t\t\tMaster Mind"
    val gameboard ="""
    |-------|-------|-------|-------|
    |       |       |       |       |
    |       |       |       |       |
    |-------|-------|-------|-------|
    |       |       |       |       |
    |       |       |       |       |
    |-------|-------|-------|-------|
    |       |       |       |       |
    |       |       |       |       |
    |-------|-------|-------|-------|
    |       |       |       |       |
    |       |       |       |       |
    |-------|-------|-------|-------|
    |       |       |       |       |
    |       |       |       |       |
    |-------|-------|-------|-------|
    |       |       |       |       |
    |       |       |       |       |
    |-------|-------|-------|-------|
    |       |       |       |       |
    |       |       |       |       |
    |-------|-------|-------|-------|
    |       |       |       |       |     right colors: 0
    |  blue |  red  | green | black |     right positions: 0
    |-------|-------|-------|-------|
     """
    val colors = "blue, green, red, yellow,\n\t\t\t pink, white, black, orange"
    val colorselection = "colors: " + colors
    title + gameboard + colorselection
  }
  def main(args:Array[String]): Unit ={

    println(gamelayout())
  }
}
