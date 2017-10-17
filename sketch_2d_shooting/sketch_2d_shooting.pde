final color red = color(255, 100, 100);
final color blue = color(100, 100, 255);
final color green = color(100, 255, 100);
final color yellow = color(255, 204, 0);
final color purple = color(255, 0, 255);
final color pink = color(255, 200, 200);
final color white = color(255, 255, 255);
final color lightGrey = color(200);
final color black = color(0, 0, 0);

final PVector playerSize = new PVector(25, 50);
final PVector bulletSize = new PVector(10, 5);
final float bulletScale = 2;
final float playerScale = 2;

final int groundNo = 1;
final int playerNo = 2;
final float playerS = 5;
final float jumpS = 10;
final float gravity = 10;
final float bulletS = 10;
final float maxFuel = 100;
final float cooldown = 50;
final float health = 100;
final float bulletDmg = 10;

boolean done = false;

PVector[] initPos;
boolean[] m, j, l, r;

Ground[] grounds;
Player[] players;
ArrayList<Bullet> bullets;

void setup()
{
  size(1600, 900);
  noStroke();
  textAlign(CENTER, CENTER);

  initPos = new PVector[playerNo];
  initPos[0] = new PVector(100, height-100-playerSize.y*playerScale);
  initPos[1] = new PVector(width-100-playerSize.x*playerScale, height-100-playerSize.y*playerScale);

  m = new boolean[playerNo];
  for (boolean i : m) 
  {
    i = false;
  }

  j = new boolean[playerNo];
  for (boolean i : j)
  {
    i = false;
  }

  l = new boolean[playerNo];
  for (boolean i : l)
  {
    i = false;
  }

  r = new boolean[playerNo];
  for (boolean i : r)
  {
    i = false;
  }

  grounds = new Ground[groundNo];
  grounds[0] = new Ground(0, height-100, width, 50, black);

  players = new Player[playerNo];
  for (int i = 0; i < playerNo; i++)
  {
    players[i] = new Player(initPos[i].x, initPos[i].y, playerSize.x*playerScale, playerSize.y*playerScale, playerS, i);
  }

  bullets = new ArrayList<Bullet>();
}

void draw()
{
  background(255);

  fill(lightGrey);
  rect(100, 100, width/2-200, 40);
  rect(width/2+100, 100, width/2-200, 40);
  fill(green);
  rect(100, 100, max(0, (width/2-200)*players[0].hp/health), 20);
  rect(width/2+100, 100, max(0, (width/2-200)*players[1].hp/health), 20);  
  fill(yellow);
  rect(100, 120, max(0, (width/2-200)*players[0].fuel/maxFuel), 20);
  rect(width/2+100, 120, max(0, (width/2-200)*players[1].fuel/maxFuel), 20);  

  for (Ground g : grounds)
  {
    g.update();
  }

  for (Player p : players)
  {
    if (p.hp <= 0) done = true;    

    p.update();

    if (l[p.id]) 
    {
      p.dir = -1;
      p.move(-1);
    }

    if (r[p.id]) 
    {
      p.dir = 1;
      p.move(1);
    }
    
    if (j[p.id] && p.fuel > 0) p.up();
    else p.down();
  }

  if (!done) {
    for (int i = bullets.size()-1; i>=0; i--)
    {
      bullets.get(i).update();
      bullets.get(i).move();
    }
  }

  if (done)
  {
    fill(lightGrey);
    textSize(50);
    if (players[0].hp == 0)
    {
      if (players[1].hp == 0) text("Both died", 0, 0, width, height);
      else text("Player 1 wins", 0, 0, width, height);
    } else
    {
      if (players[1].hp == 0) text("Player 0 wins", 0, 0, width, height);
    }
  }
}

void keyPressed()
{
  if (!done)
  {
    if (key == CODED) setMove(keyCode, true);
    else setMove(key, true);
  }
}

void keyReleased() 
{
  if (!done)
  {
    if (key == CODED) setMove(keyCode, false);
    else setMove(key, false);
  }
}

void setMove(int k, boolean b) {
  switch (k) {
  case 'a':
    l[0] = b;
    m[0] = b;
    break;
  case 'd':
    r[0] = b;        
    m[0] = b;
    break;
  case LEFT:
    l[1] = b;
    m[1] = b;
    break;
  case RIGHT:
    r[1] = b;        
    m[1] = b;
    break;
  case 'w':
    j[0] = b;
    break;
  case UP:
    j[1] = b;
    ;
    break;
  case 's':
    players[0].shoot();
    break;
  case DOWN:
    players[1].shoot();
    break;
  }
}