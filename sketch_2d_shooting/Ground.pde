class Ground extends Rect
{
  color c;

  Ground(float x, float y, float w, float h, color c)
  {
    super(x, y, w, h);
    this.c = c;
  }

  void update()
  {
    fill(c);
    rect(x, y, w, h);
  }
}