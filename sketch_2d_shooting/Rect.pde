class Rect
{
  float x, y, w, h;

  Rect(float x, float y, float w, float h)
  {
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
  }

  boolean inside(Rect other)
  {
    float left = x;
    float top = y;
    float right = x + w;
    float bottom = y + h;

    float _left = other.x;
    float _top = other.y;
    float _right = other.x + other.w;
    float _bottom = other.y + other.h;

    return !(left >= _right ||
      right <= _left ||
      top >= _bottom ||
      bottom <= _top);
  }
}