class Bullet extends Rect
{
  float s;
  color c;
  int dir, id;

  Bullet(float x, float y, float w, float h, int dir, float s, color c, int id)
  {
    super(x, y, w, h);
    this.s = s;
    this.c = c;
    this.dir = dir;
  }

  void move()
  {

    x += dir*s;

    if (!(x <= width-w && x >= 0)) bullets.remove(id);

    for (Ground g : grounds)
    {
      if (inside(g))
      {
        bullets.remove(id);
      }
    }

    for (Player p : players)
    {
      if (inside(p))
      {
        println(p.id);
        p.hp -= bulletDmg;
        bullets.remove(id);
      }
    }
  }

  void update()
  {
    fill(c);
    rect(x, y, w, h);
  }
}