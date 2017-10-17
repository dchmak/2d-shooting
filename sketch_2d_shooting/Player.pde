class Player extends Rect
{
  int dir, id;
  float s, cd, hp, fuel, v, a;

  Player(float x, float y, float w, float h, float s, int id)
  {
    super(x, y, w, h);
    this.s = s;
    this.id = id;
    cd = 0;
    hp = health;
  }  

  void move(int dir)
  {
    if (m[id])
    {
      if ((x + dir*s) <= width-w && (x + dir*s) >= 0)
      {
        x += dir*s;

        for (Ground g : grounds)
        {
          if (inside(g)) x -= dir*s;
        }

        for (Player p : players)
        {
          if (inside(p) && id != p.id)
          {
            x -= dir*s;
          }
        }
      }
    }
  }

  void up()
  {
    if (y-jumpS >= 0)
    {
      y -= jumpS;

      for (Ground g : grounds)
      {
        if (inside(g)) y += jumpS;
      }

      fuel--;
      fuel = max(fuel, 0);
    }
  }

  void down()
  {
    if (y+gravity <= height+h)
    {
      y += gravity;
      for (Ground g : grounds)
      {
        if (inside(g)) y -= gravity;
      }
    } else
    {
      hp = 0;
    }
  }

  void shoot()
  {
    if (cd == 0) 
    {
      if (dir == 1) /*right*/ bullets.add(new Bullet(x+w, y+h/3, bulletSize.x*bulletScale, bulletSize.y*bulletScale, dir, bulletS, black, id));
      else /*left*/ bullets.add(new Bullet(x-bulletSize.x*bulletScale, y+h/3, bulletSize.x*bulletScale, bulletSize.y*bulletScale, dir, bulletS, black, id));
      cd = cooldown;
    }
  }

  void update()
  {  
    switch(id)
    {      
    case 0: 
      fill(red);
      break;
    case 1: 
      fill(blue);
      break;
    default: 
      println("Player overflow");
      exit();
    }
    rect(x, y, w, h);

    for (Ground g : grounds)
    {
      if (touchAbove(g)) fuel++;
      fuel = min(fuel, maxFuel);
    }
    if (cd > 0) cd--;
  }

  boolean touchAbove(Rect other)
  {
    return (y+h == other.y);
  }
}