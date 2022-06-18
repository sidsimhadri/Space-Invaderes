import tester.Tester;
import java.util.Random;

import javalib.funworld.World;
import javalib.funworld.WorldScene;
import javalib.worldimages.FontStyle;
import javalib.worldimages.FromFileImage;
import javalib.worldimages.ScaleImage;
import javalib.worldimages.TextImage;
import javalib.worldimages.WorldEnd;
import javalib.worldimages.RotateImage;
import java.awt.Color;

//represents all objects in the game
interface IGamePiece {
  // draws this IDrawImage
  WorldScene draw(WorldScene scene);

  // fires a new InvaderBullet
  IGamePiece fireBullet();

  // should a new InvaderBullet be fired
  boolean isFire();

  // updates the condition of this IDrawImage (for onTick Handler)
  IGamePiece update();

  // returns true iff if this IDrawImage is in the scene
  boolean inFrame();

  // returns true iff the given coordinates are within this object
  boolean isHit(int x, int y);

  // returns true iff if this IDrawImage is hitting hitted
  boolean isHitting(IGamePiece hitted);
}

// represents a spaceship
class Spaceship implements IGamePiece {
  int x;
  ScaleImage image;

  Spaceship(int x) {
    this.x = x;
    this.image = new ScaleImage(new FromFileImage("ship.png"), .25);
  }
  /*
   * Template:
   * Fields:
   * this.x...int
   * this.image...ScaleImage
   * Methods:
   * this.moveR()...SpaceShip
   * this.moveL()...SpaceShip
   * this.draw(WorldScene scene)...WorldScene
   * this.fireBullet()...IGamePiece
   * this.isFire()...boolean
   * this.update()...IGamePiece
   * this.inFrame()...boolean
   * this.isHit(int x, int y)...boolean
   * this.isHitting(IGamePiece hitted)...boolean
   * Methods of fields:
   * all methods for WorldImages can be applied to this.image
   */

  // moves this spaceship to the right
  Spaceship moveR() {
    // this class template
    return new Spaceship(x + 5);
  }

  // moves this spaceship to the left
  Spaceship moveL() {
    // this class template
    return new Spaceship(x - 5);
  }

  // draws this spaceship
  public WorldScene draw(WorldScene scene) {
    /*
     * this class template
     * Methods for parameters:
     * methods for WorldScene can be applied to scene
     */
    return scene.placeImageXY(image, this.x, 450);
  }

  // returns this spaceship
  public IGamePiece fireBullet() {
    // this class template
    return this;
  }

  // returns false
  public boolean isFire() {
    // this class template
    return false;
  }

  // returns this spaceship
  public IGamePiece update() {
    // this class template
    return this;
  }

  // returns true iff this spaceship is in frame
  public boolean inFrame() {
    // this class template
    return this.x >= 0 && this.x <= 500;
  }

  // returns true iff the given coordinates are within this object
  public boolean isHit(int x, int y) {
    // this class template
    return x < this.x + 35 && x > this.x - 35 && y > 440;
  }

  // returns false (unecessary because invader bullets don't need to disappear
  // since the game ends as soon as the spaceship is hit)
  public boolean isHitting(IGamePiece hitted) {
    /*
     * this class template:
     * Methods of parameters:
     * hitted.draw(WorldScene scene)...WorldScene
     * hitted.fireBullet()...IGamePiece
     * hitted.isFire()...boolean
     * hitted.update()...IGamePiece
     * hitted.inFrame()...boolean
     * hitted.isHit(int x, int y)...boolean
     * hitted.isHitting(IGamePiece hitted)...boolean
     */
    return false;
  }
}

// represents an invader bullet
class InvaderBullet implements IGamePiece {
  int x;
  int y;
  ScaleImage image;

  InvaderBullet(int x, int y) {
    this.x = x;
    this.y = y;
    this.image = new ScaleImage(new RotateImage(new FromFileImage("bad guy.png"), 90), .1);
  }
  /*
   * Template:
   * Fields:
   * this.x...int
   * this.y...int
   * this.image...ScaleImage
   * Methods:
   * this.draw(WorldScene scene)...WorldScene
   * this.fireBullet()...IGamePiece
   * this.isFire()...boolean
   * this.update()...IGamePiece
   * this.inFrame()...boolean
   * this.isHit(int x, int y)...boolean
   * this.isHitting(IGamePiece hitted)...boolean
   * Methods of fields:
   * all methods for WorldImages can be applied to this.image
   */

  // draw this invader bullet
  public WorldScene draw(WorldScene scene) {
    /*
     * this class template
     * Methods for parameters:
     * methods for WorldScene can be applied to scene
     */
    return scene.placeImageXY(image, this.x, this.y);
  }

  // returns this invader bullet
  public IGamePiece fireBullet() {
    // this class template
    return this;
  }

  // returns false
  public boolean isFire() {
    // this class template
    return false;
  }

  // moves this invader bullet
  public IGamePiece update() {
    // this class template
    return new InvaderBullet(this.x, this.y + 1);
  }

  // returns true iff this invader bullet is in the scene
  public boolean inFrame() {
    // this class template
    return this.y < 500;
  }

  // returns false (unecessary because invader bullets don't need to disappear
  // when hit since the game ends)
  public boolean isHit(int x, int y) {
    // this class template
    return false;
  }

  // returns true iff this invader bullet is hitting hitted
  public boolean isHitting(IGamePiece hitted) {
    /*
     * this class template:
     * Methods of parameters:
     * hitted.draw(WorldScene scene)...WorldScene
     * hitted.fireBullet()...IGamePiece
     * hitted.isFire()...boolean
     * hitted.update()...IGamePiece
     * hitted.inFrame()...boolean
     * hitted.isHit(int x, int y)...boolean
     * hitted.isHitting(IGamePiece hitted)...boolean
     */
    return hitted.isHit(this.x, this.y);
  }
}

// represents a ship bullet
class ShipBullet implements IGamePiece {
  int x;
  int y;
  ScaleImage image;

  ShipBullet(int x, int y) {
    this.x = x;
    this.y = y;
    this.image = new ScaleImage(new RotateImage(new FromFileImage("bullet.png"), 270), .015);
  }
  /*
   * Template:
   * Fields:
   * this.x...int
   * this.y...int
   * this.image...ScaleImage
   * Methods:
   * this.draw(WorldScene scene)...WorldScene
   * this.fireBullet()...IGamePiece
   * this.isFire()...boolean
   * this.update()...IGamePiece
   * this.inFrame()...boolean
   * this.isHit(int x, int y)...boolean
   * this.isHitting(IGamePiece hitted)...boolean
   * Methods of fields:
   * all methods for WorldImages can be applied to this.image
   */

  // draws this ship bullet
  public WorldScene draw(WorldScene scene) {
    /*
     * this class template
     * Methods for parameters:
     * methods for WorldScene can be applied to scene
     */
    return scene.placeImageXY(image, this.x, this.y);
  }

  // returns this ship bullet
  public IGamePiece fireBullet() {
    // this class template
    return this;
  }

  // moves this ship bullet
  public IGamePiece update() {
    // this class template
    return new ShipBullet(this.x, this.y - 2);
  }

  // returns false
  public boolean isFire() {
    // this class template
    return false;
  }

  // returns true iff this shipbullet is in the scene
  public boolean inFrame() {
    // this class template
    return this.y > 0;
  }

  // returns true iff x and y are hitting this shipbullet
  public boolean isHit(int x, int y) {
    // this class template
    return this.x < x + 25 && this.x > x - 25 && this.y - 15 < y;
  }

  // returns true if this ship bullet is hitting hitted
  public boolean isHitting(IGamePiece hitted) {
    /*
     * this class template:
     * Methods of parameters:
     * hitted.draw(WorldScene scene)...WorldScene
     * hitted.fireBullet()...IGamePiece
     * hitted.isFire()...boolean
     * hitted.update()...IGamePiece
     * hitted.inFrame()...boolean
     * hitted.isHit(int x, int y)...boolean
     * hitted.isHitting(IGamePiece hitted)...boolean
     */
    return hitted.isHit(this.x, this.y);
  }

}

// represents a space invader
class Invader implements IGamePiece {
  int x;
  int y;
  ScaleImage image;
  int val;
  Random rand;

  Invader(int x, int y, int z, Random rand) {
    this.x = x;
    this.y = y;
    this.image = new ScaleImage(new FromFileImage("alien.png"), .025);
    this.rand = rand;
    this.val = z;
  }

  // initializes r to be a random number
  Invader(int x, int y) {
    this.x = x;
    this.y = y;
    this.image = new ScaleImage(new FromFileImage("alien.png"), .025);
    this.rand = new Random();
    this.val = this.rand.nextInt(100);

  }
  /*
   * Template:
   * Fields:
   * this.x...int
   * this.y...int
   * this.image...ScaleImage
   * this.val...int
   * this.rand...Random
   * Methods:
   * this.draw(WorldScene scene)...WorldScene
   * this.fireBullet()...IGamePiece
   * this.isFire()...boolean
   * this.update()...IGamePiece
   * this.inFrame()...boolean
   * this.isHit(int x, int y)...boolean
   * this.isHitting(IGamePiece hitted)...boolean
   * Methods of fields:
   * all methods for WorldImages can be applied to this.image
   * all methods for Random can be applied to this.rand
   */

  // draws this invader
  public WorldScene draw(WorldScene scene) {
    /*
     * this class template
     * Methods for parameters:
     * methods for WorldScene can be applied to scene
     */
    return scene.placeImageXY(image, this.x, this.y);
  }

  // creates new bullet under the invader
  public IGamePiece fireBullet() {
    // this class template
    return new InvaderBullet(this.x, this.y + 5);
  }

  // changes the random number of this invader
  public IGamePiece update() {
    // this class template
    return new Invader(this.x, this.y, this.rand.nextInt(100), this.rand);
  }

  // returns true iff this invader should fire a bullet
  public boolean isFire() {
    // this class template
    return this.val < 8;
  }

  // returns true
  public boolean inFrame() {
    // this class template
    return true;
  }

  // returns true iff x and y are hitting this invader
  public boolean isHit(int x, int y) {
    // this class template
    return x < this.x + 25 && x > this.x - 25 && y < this.y + 17;
  }

  // returns true if this invader is hitting hitted
  public boolean isHitting(IGamePiece hitted) {
    /*
     * this class template:
     * Methods of parameters:
     * hitted.draw(WorldScene scene)...WorldScene
     * hitted.fireBullet()...IGamePiece
     * hitted.isFire()...boolean
     * hitted.update()...IGamePiece
     * hitted.inFrame()...boolean
     * hitted.isHit(int x, int y)...boolean
     * hitted.isHitting(IGamePiece hitted)...boolean
     */
    return hitted.isHit(this.x, this.y);
  }

}

// represents a list
interface IList<T> {
  // applies a function that folds the list into one output type U
  <U> U fold(IFunc<T, U, U> fun, U base);

  // maps a function over every element in the list
  <U> IList<U> map(IFunc1<T, U> fun);

  // maps a function over the elements that meet a condition in the list
  <U> IList<U> condMap(IPred<T> pred, IFunc1<T, U> fun);

  // returns the count of this list
  int count();

  // filters the list by the given predicate
  IList<T> filter(IPred<T> pred);

  // returns true iff any of the elements in this list meet the given predicate
  boolean ormap(IPred<T> pred);

  // appends the given list to this list
  IList<T> append(IList<T> other);
}

// represents an empty list
class MtList<T> implements IList<T> {
  /*
   * Template:
   * Methods:
   * this.fold(IFunc<T, U, U> fun, U base)...U
   * this.map(IFunc1<T, U> fun)...IList<U>
   * this.condMap(IPred<T> pred, IFunc1<T, U> fun)...IList<U>
   * this.count1()...int
   * this.filter(IPred<T> pred)...IList<T>
   * this.orMap(IPred<T> pred)...boolean
   * this.append(IList<T> other)...IList<T>
   */

  // returns the base case
  public <U> U fold(IFunc<T, U, U> fun, U base) {
    /*
     * this class template
     * Methods of parameters:
     * fun.apply(T t, U u)...U
     */
    return base;
  }

  // returns an MtList<U>
  public <U> IList<U> map(IFunc1<T, U> fun) {
    /*
     * this class template
     * Methods of parameters:
     * fun.apply(T)...U
     */
    return new MtList<U>();
  }

  // returns an MtList<U>
  public <U> IList<U> condMap(IPred<T> pred, IFunc1<T, U> fun) {
    /*
     * this class template
     * Methods of parameters:
     * pred.apply(T)...boolean
     * fun.apply(T)...U
     */
    return new MtList<U>();
  }

  // returns 0
  public int count() {
    // this class template
    return 0;
  }

  // returns this MtList<T>
  public IList<T> filter(IPred<T> pred) {
    /*
     * this class template
     * Methods of parameters:
     * pred.apply(T)...boolean
     */
    return this;
  }

  // returns false
  public boolean ormap(IPred<T> pred) {
    /*
     * this class template
     * Methods of parameters:
     * pred.apply(T)...boolean
     */
    return false;
  }

  // returns the given list
  public IList<T> append(IList<T> other) {
    /*
     * this class template
     * Methods of parameters:
     * other.fold(IFunc<T, U, U> fun, U base)...U
     * other.map(IFunc1<T, U> fun)...IList<U>
     * other.condMap(IPred<T> pred, IFunc1<T, U> fun)...IList<U>
     * other.count1()...int
     * other.filter(IPred<T> pred)...IList<T>
     * other.orMap(IPred<T> pred)...boolean
     * other.append(IList<T> other)...IList<T>
     */
    return other;
  }

}

// represents a cons list
class ConsList<T> implements IList<T> {
  T first;
  IList<T> rest;

  ConsList(T first, IList<T> rest) {
    this.first = first;
    this.rest = rest;
  }
  /*
   * Template:
   * Methods:
   * this.fold(IFunc<T, U, U> fun, U base)...U
   * this.map(IFunc1<T, U> fun)...IList<U>
   * this.condMap(IPred<T> pred, IFunc1<T, U> fun)...IList<U>
   * this.count1()...int
   * this.filter(IPred<T> pred)...IList<T>
   * this.orMap(IPred<T> pred)...boolean
   * this.append(IList<T> other)...IList<T>
   * Methods of Fields:
   * this.rest.fold(IFunc<T, U, U> fun, U base)...U
   * this.rest.map(IFunc1<T, U> fun)...IList<U>
   * this.rest.condMap(IPred<T> pred, IFunc1<T, U> fun)...IList<U>
   * this.rest.count1()...int
   * this.rest.filter(IPred<T> pred)...IList<T>
   * this.rest.orMap(IPred<T> pred)...boolean
   * this.rest.append(IList<T> other)...IList<T>
   */

  // folds all the elements of this list into one U using the given IFunc
  public <U> U fold(IFunc<T, U, U> fun, U base) {
    /*
     * this class template
     * Methods of parameters:
     * fun.apply(T, U)...U
     */
    return fun.apply(this.first, this.rest.fold(fun, base));
  }

  // maps the given IFunc1 over every element of this cons list
  public <U> IList<U> map(IFunc1<T, U> fun) {
    /*
     * this class template
     * Methods of parameters:
     * fun.apply(T)...U
     */
    return new ConsList<U>(fun.apply(this.first), this.rest.map(fun));
  }

  // maps the given IFunc1 over every element that meets pred of this cons list
  public <U> IList<U> condMap(IPred<T> pred, IFunc1<T, U> fun) {
    /*
     * this class template
     * Methods of parameters:
     * pred.apply(T)...boolean
     * fun.apply(T)...U
     */
    if (pred.apply(this.first)) {
      return new ConsList<U>(fun.apply(this.first), this.rest.condMap(pred, fun));
    }
    else {
      return this.rest.condMap(pred, fun);
    }
  }

  // returns the length of this cons list
  public int count() {
    // this class template
    return 1 + this.rest.count();
  }

  // filters this conslist by pred
  public IList<T> filter(IPred<T> pred) {
    /*
     * this class template
     * Methods of parameters:
     * pred.apply(T)...boolean
     */
    if (pred.apply(this.first)) {
      return new ConsList<T>(this.first, this.rest.filter(pred));
    }
    else {
      return this.rest.filter(pred);
    }
  }

  // returns true if an element of this cons list meets pred
  public boolean ormap(IPred<T> pred) {
    /*
     * this class template
     * Methods of parameters:
     * pred.apply(T)...boolean
     */
    return pred.apply(this.first) || this.rest.ormap(pred);
  }

  // appends other to this cons list
  public IList<T> append(IList<T> other) {
    /*
     * this class template
     * Methods of parameters:
     * other.fold(IFunc<T, U, U> fun, U base)...U
     * other.map(IFunc1<T, U> fun)...IList<U>
     * other.condMap(IPred<T> pred, IFunc1<T, U> fun)...IList<U>
     * other.count1()...int
     * other.filter(IPred<T> pred)...IList<T>
     * other.orMap(IPred<T> pred)...boolean
     * other.append(IList<T> other)...IList<T>
     */
    return new ConsList<T>(this.first, this.rest.append(other));
  }
}

// represents a predicate
interface IPred<T> {
  // applies this predicate to t
  boolean apply(T t);
}

// is this object hitting its hitted
class IsHitting implements IPred<IGamePiece> {

  IGamePiece hitted;

  IsHitting(IGamePiece hitted) {
    this.hitted = hitted;
  }

  /*
   * Template:
   * Fields:
   * this.hitted...IGamePiece
   * Methods:
   * this.apply(IGamePiece hitter)...boolean
   * Methods of fields:
   * this.hitted.draw(WorldScene scene)...WorldScene
   * this.hitted.fireBullet()...IGamePiece
   * this.hitted.isFire()...boolean
   * this.hitted.update()...IGamePiece
   * this.hitted.inFrame()...boolean
   * this.hitted.isHit(int x, int y)...boolean
   * this.hitted.isHitting(IGamePiece hitted)...boolean
   */
  // checks if the given IDrawImage is hitting this.hitted
  public boolean apply(IGamePiece hitter) {
    /*
     * this class template
     * Methods of parameters:
     * hitter.draw(WorldScene scene)...WorldScene
     * hitter.fireBullet()...IGamePiece
     * hitter.isFire()...boolean
     * hitter.update()...IGamePiece
     * hitter.inFrame()...boolean
     * hitter.isHit(int x, int y)...boolean
     * hitter.isHitting(IGamePiece hitted)...boolean
     */
    return hitter.isHitting(this.hitted);
  }
}

// returns true iff an IDrawImage is not being hit
class ListNotHitting implements IPred<IGamePiece> {

  IList<IGamePiece> hitters;

  ListNotHitting(IList<IGamePiece> hitters) {
    this.hitters = hitters;
  }

  /*
   * Template:
   * Fields:
   * this.bullets...IList<IGamePiece>
   * Methods:
   * this.apply(IGamePiece hitted)...boolean
   * Methods of fields:
   * this.hitters.draw(WorldScene scene)...WorldScene
   * this.hitters.fireBullet()...IGamePiece
   * this.hitters.isFire()...boolean
   * this.hitters.update()...IGamePiece
   * this.bullets.inFrame()...boolean
   * this.hitters.isHit(int x, int y)...boolean
   * this.hitters.isHitting(IGamePiece hitted)...boolean
   */
  // returns true iff none of this.hitters are hitting hitted
  public boolean apply(IGamePiece hitted) {
    /*
     * this class template
     * Methods of parameters:
     * hitted.draw(WorldScene scene)...WorldScene
     * hitted.fireBullet()...IGamePiece
     * hitted.isFire()...boolean
     * hitted.update()...IGamePiece
     * hitted.inFrame()...boolean
     * hitted.isHit(int x, int y)...boolean
     * hitted.isHitting(IGamePiece hitted)...boolean
     */
    return !this.hitters.ormap(new IsHitting(hitted));
  }
}

// checks if an IDrawImage should fire a bullet
class Fire implements IPred<IGamePiece> {
  /*
   * Template:
   * Methods:
   * this.apply(IGamePiece t)...boolean
   */
  // returns true if t should fire a new bullet
  public boolean apply(IGamePiece t) {
    /*
     * this class template
     * Methods of parameters:
     * t.draw(WorldScene scene)...WorldScene
     * t.fireBullet()...IGamePiece
     * t.isFire()...boolean
     * t.update()...IGamePiece
     * t.inFrame()...boolean
     * t.isHit(int x, int y)...boolean
     * t.isHitting(IGamePiece hitted)...boolean
     */
    return t.isFire();
  }
}

// checks if an IDrawImage is in frame
class InFrame implements IPred<IGamePiece> {
  /*
   * Template:
   * Methods:
   * this.apply(IGamePiece t)...boolean
   */
  // returns true if t is in frame
  public boolean apply(IGamePiece t) {
    /*
     * this class template
     * Methods of parameters:
     * t.draw(WorldScene scene)...WorldScene
     * t.fireBullet()...IGamePiece
     * t.isFire()...boolean
     * t.update()...IGamePiece
     * t.inFrame()...boolean
     * t.isHit(int x, int y)...boolean
     * t.isHitting(IGamePiece hitted)...boolean
     */
    return t.inFrame();
  }
}

// represents a function with one input
interface IFunc1<X, Y> {
  Y apply(X x);
}

// updates a given IDrawImage
class Update implements IFunc1<IGamePiece, IGamePiece> {
  /*
   * Template:
   * Methods:
   * this.apply(IGamePiece x)...IGamePiece
   */

  // updates x using its apply function
  public IGamePiece apply(IGamePiece x) {
    /*
     * this class template
     * Methods of parameters:
     * x.draw(WorldScene scene)...WorldScene
     * x.fireBullet()...IGamePiece
     * x.isFire()...boolean
     * x.update()...IGamePiece
     * x.inFrame()...boolean
     * x.isHit(int x, int y)...boolean
     * x.isHitting(IGamePiece hitted)...boolean
     */
    return x.update();
  }
}

// fires a new invader bullet from a given IDrawImage
class FireInvaderBullet implements IFunc1<IGamePiece, IGamePiece> {
  /*
   * Template:
   * Methods:
   * this.apply(IGamePiece x)...IGamePiece
   */
  // fires a new bullet from x
  public IGamePiece apply(IGamePiece x) {
    /*
     * this class template
     * Methods of parameters:
     * x.draw(WorldScene scene)...WorldScene
     * x.fireBullet()...IGamePiece
     * x.isFire()...boolean
     * x.update()...IGamePiece
     * x.inFrame()...boolean
     * x.isHit(int x, int y)...boolean
     * x.isHitting(IGamePiece hitted)...boolean
     */
    return x.fireBullet();
  }

}

interface IFunc<X, Y, Z> {

  Z apply(X x, Y y);
}

// draws a given IDrawImage on the WorldScene
class Draw implements IFunc<IGamePiece, WorldScene, WorldScene> {
  /*
   * Template:
   * Methods:
   * this.apply(IGamePiece i, WorldScene scene)...WorldScene
   */
  // draws i on scene
  public WorldScene apply(IGamePiece i, WorldScene scene) {
    /*
     * this class template
     * Methods of parameters:
     * i.draw(WorldScene scene)...WorldScene
     * i.fireBullet()...IGamePiece
     * i.isFire()...boolean
     * i.update()...IGamePiece
     * i.inFrame()...boolean
     * i.isHit(int x, int y)...boolean
     * i.isHitting(IGamePiece hitted)...boolean
     * all methods for WorldScene s can be applied to scene
     */
    return i.draw(scene);
  }

}

// represents the World
class ShipWorld extends World {

  // the world's state includes these
  Spaceship ship;
  IList<IGamePiece> shipBullets;
  IList<IGamePiece> invaders;
  IList<IGamePiece> invaderBullets;

  ShipWorld() {
    this.ship = new Spaceship(15);
    this.shipBullets = new MtList<IGamePiece>();
    this.invaders = new ConsList<IGamePiece>(new Invader(50, 20), new ConsList<IGamePiece>(
        new Invader(100, 20),
        new ConsList<IGamePiece>(new Invader(150, 20), new ConsList<IGamePiece>(
            new Invader(200, 20),
            new ConsList<IGamePiece>(new Invader(250, 20), new ConsList<IGamePiece>(
                new Invader(300, 20),
                new ConsList<IGamePiece>(new Invader(350, 20), new ConsList<IGamePiece>(
                    new Invader(400, 20),
                    new ConsList<IGamePiece>(new Invader(450, 20), new ConsList<IGamePiece>(
                        new Invader(50, 50),
                        new ConsList<IGamePiece>(new Invader(100, 50),
                            new ConsList<IGamePiece>(new Invader(150, 50), new ConsList<IGamePiece>(
                                new Invader(200, 50), new ConsList<IGamePiece>(new Invader(250,
                                    50), new ConsList<IGamePiece>(new Invader(300, 50), new ConsList<IGamePiece>(new Invader(350, 50), new ConsList<IGamePiece>(new Invader(400, 50), new ConsList<IGamePiece>(new Invader(450, 50), new ConsList<IGamePiece>(new Invader(50, 80), new ConsList<IGamePiece>(new Invader(100, 80), new ConsList<IGamePiece>(new Invader(150, 80), new ConsList<IGamePiece>(new Invader(200, 80), new ConsList<IGamePiece>(new Invader(250, 80), new ConsList<IGamePiece>(new Invader(300, 80), new ConsList<IGamePiece>(new Invader(350, 80), new ConsList<IGamePiece>(new Invader(400, 80), new ConsList<IGamePiece>(new Invader(450, 80), new ConsList<IGamePiece>(new Invader(50, 110), new ConsList<IGamePiece>(new Invader(100, 110), new ConsList<IGamePiece>(new Invader(150, 110), new ConsList<IGamePiece>(new Invader(200, 110), new ConsList<IGamePiece>(new Invader(250, 110), new ConsList<IGamePiece>(new Invader(300, 110), new ConsList<IGamePiece>(new Invader(350, 110), new ConsList<IGamePiece>(new Invader(400, 110), new ConsList<IGamePiece>(new Invader(450, 110), new MtList<IGamePiece>()))))))))))))))))))))))))))))))))))));
    this.invaderBullets = new MtList<IGamePiece>();
  }

  ShipWorld(Spaceship s, IList<IGamePiece> b, IList<IGamePiece> i, IList<IGamePiece> a) {
    this.ship = s;
    this.shipBullets = b;
    this.invaders = i;
    this.invaderBullets = a;

  }

  /*
   * Template:
   * Fields:
   * this.ship...SpaceShip
   * this.shipBullets...IList<IGamePiece>
   * this.invaders...IList<IGamePiece>
   * this.invaderBullets...IList<IGamePiece>
   * Methods:
   * this.makeScene()...WorldScene
   * this.makeLoseScene()...WorldScene
   * this.makeWinScene()...WorldScene
   * this.onKeyEvent(String key)...World
   * this.onTick()...World
   * this.worldEnds()...World
   * Methods for Fields:
   * this.ship.moveR()...SpaceShip
   * this.ship.moveL()...SpaceShip
   * this.ship.draw(WorldScene scene)...WorldScene
   * this.ship.fireBullet()...IGamePiece
   * this.ship.isFire()...boolean
   * this.ship.update()...IGamePiece
   * this.ship.inFrame()...boolean
   * this.ship.isHit(int x, int y)...boolean
   * this.ship.isHitting(IGamePiece hitted)...boolean
   * -------------------------------------------------------
   * this.shipBullets.fold(IFunc<IGamePiece, U, U> fun, U base)...U
   * this.shipBullets.map(IFunc1<IGamePiece, U> fun)...IList<U>
   * this.shipBullets.condMap(IPred<IGamePiece> pred, IFunc1<IGamePiece, U>
   * fun)...IList<U>
   * this.shipBullets.count1()...int
   * this.shipBullets.filter(IPred<IGamePiece> pred)...IList<IGamePiece>
   * this.shipBullets.orMap(IPred<IGamePiece> pred)...boolean
   * this.shipBullets.append(IList<IGamePiece> other)...IList<IGamePiece>
   * -------------------------------------------------------
   * this.invaders.fold(IFunc<IGamePiece, U, U> fun, U base)...U
   * this.invaders.map(IFunc1<IGamePiece, U> fun)...IList<U>
   * this.invaders.condMap(IPred<IGamePiece> pred, IFunc1<IGamePiece, U>
   * fun)...IList<U>
   * this.invaders.count1()...int
   * this.invaders.filter(IPred<IGamePiece> pred)...IList<IGamePiece>
   * this.invaders.orMap(IPred<IGamePiece> pred)...boolean
   * this.invaders.append(IList<IGamePiece> other)...IList<IGamePiece>
   * -------------------------------------------------------
   * this.invaderBullets.fold(IFunc<IGamePiece, U, U> fun, U base)...U
   * this.invaderBullets.map(IFunc1<IGamePiece, U> fun)...IList<U>
   * this.invaderBullets.condMap(IPred<IGamePiece> pred, IFunc1<IGamePiece, U>
   * fun)...IList<U>
   * this.invaderBullets.count1()...int
   * this.invaderBullets.filter(IPred<IGamePiece> pred)...IList<IGamePiece>
   * this.invaderBullets.orMap(IPred<IGamePiece> pred)...boolean
   * this.invaderBullets.append(IList<IGamePiece> other)...IList<IGamePiece>
   */

  // makes the scene
  @Override
  public WorldScene makeScene() {
    // this class template
    WorldScene scene = this.getEmptyScene()
        .placeImageXY(new ScaleImage(new FromFileImage("background.png"), 1.2), 250, 250);

    return this.invaderBullets.fold(new Draw(), this.shipBullets.fold(new Draw(),
        this.ship.draw(this.invaders.fold(new Draw(), scene))));
  }

  // makes the lose scene
  public WorldScene makeLoseScene() {
    // this class template
    WorldScene scene = this.getEmptyScene()
        .placeImageXY(new ScaleImage(new FromFileImage("background.png"), 1.2), 250, 250);
    return scene.placeImageXY(new TextImage("You Got Hit:(", 20,
        FontStyle.BOLD_ITALIC, Color.white), 250, 250);
  }

  // makes the win scene
  public WorldScene makeWinScene() {
    // this class template
    WorldScene scene = this.getEmptyScene()
        .placeImageXY(new ScaleImage(new FromFileImage("background.png"), 1.2), 250, 250);
    return scene.placeImageXY(new TextImage("You Beat the Invaders!!!", 20,
        FontStyle.BOLD_ITALIC, Color.white), 250, 250);
  }

  // reacts to key events
  public World onKeyEvent(String key) {
    /*
     * this class template
     * Methods of parameters:
     * all methods for class String can be applied to key
     */
    if (key.equals("left")) {
      if (this.ship.x > 0) {
        return new ShipWorld(this.ship.moveL(), this.shipBullets, this.invaders,
            this.invaderBullets);
      }

      else {
        return this;
      }
    }
    if (key.equals("right")) {
      if (this.ship.x < 500) {
        return new ShipWorld(this.ship.moveR(), this.shipBullets, this.invaders,
            this.invaderBullets);
      }
      else {
        return this;
      }
    }
    if (key.equals(" ")) {
      if (this.shipBullets.count() < 3) {
        return new ShipWorld(this.ship,
            new ConsList<IGamePiece>(new ShipBullet(this.ship.x, 390), this.shipBullets),
            this.invaders, this.invaderBullets);
      }
      else {
        return new ShipWorld(this.ship, this.shipBullets, this.invaders, this.invaderBullets);
      }
    }
    else {
      return this;
    }
  }

  // reacts to clock ticks
  public World onTick() {
    // this class template
    if (this.invaderBullets.count() + this.invaders.filter(new Fire()).count() < 7) {

      return new ShipWorld(this.ship,
          this.shipBullets.map(new Update()).filter(new InFrame())
              .filter(new ListNotHitting(this.invaders)),
          this.invaders.map(new Update()).filter(new ListNotHitting(this.shipBullets)),
          this.invaderBullets.append(this.invaders.condMap(new Fire(), new FireInvaderBullet()))
              .map(new Update())
              .filter(new InFrame()));
    }
    else {
      return new ShipWorld(this.ship,
          this.shipBullets.map(new Update()).filter(new InFrame())
              .filter(new ListNotHitting(this.invaders)),
          this.invaders.map(new Update()).filter(new ListNotHitting(this.shipBullets)),
          this.invaderBullets.filter(new InFrame()).map(new Update()));
    }
  }

  // returns the appropriate world end scene
  public WorldEnd worldEnds() {
    // this class template
    if (this.invaderBullets.ormap(new IsHitting(this.ship))) {
      return new WorldEnd(true, this.makeLoseScene());
    }
    if (this.invaders.count() < 1) {
      return new WorldEnd(true, this.makeWinScene());
    }
    else {
      return new WorldEnd(false, this.makeScene());
    }
  }
}

// the "test", that is the runner of our game.
class ExamplesRunShipWorld {
  Spaceship ship120 = new Spaceship(-5);
  Spaceship ship1 = new Spaceship(5);
  Spaceship ship2 = new Spaceship(10);
  IGamePiece ship0 = new Spaceship(0);
  IGamePiece ship15 = new Spaceship(15);
  IGamePiece invader0 = new Invader(1, 2);
  IGamePiece invader1 = new Invader(7, 7, 5, new Random(2));
  IGamePiece invader2 = new Invader(100, 100, 100, new Random(8));
  IGamePiece shipBullet1 = new ShipBullet(7, 30);
  IGamePiece shipBullet2 = new ShipBullet(7, -10);
  IGamePiece shipBullet0 = new ShipBullet(1, 2);
  IGamePiece invaderBullet1 = new InvaderBullet(7, 12);
  IGamePiece invaderBullet2 = new InvaderBullet(7, 600);
  WorldScene empty = new WorldScene(500, 500);
  WorldScene sceneShip1 = this.empty
      .placeImageXY(new ScaleImage(new FromFileImage("ship.png"), .25), 5, 450);
  WorldScene sceneInvader1 = this.empty
      .placeImageXY(new ScaleImage(new FromFileImage("alien.png"), .025), 7, 7);
  WorldScene sceneShipBullet1 = this.empty.placeImageXY(
      new ScaleImage(new RotateImage(new FromFileImage("bullet.png"), 270), .015), 7, 30);
  WorldScene sceneInvaderBullet1 = this.empty.placeImageXY(
      new ScaleImage(new RotateImage(new FromFileImage("bad guy.png"), 90), .1), 7, 12);
  IList<IGamePiece> mt = new MtList<IGamePiece>();
  IList<IGamePiece> listInvader = new ConsList<IGamePiece>(invader0,
      new ConsList<IGamePiece>(invader1,
          new ConsList<IGamePiece>(invader2, mt)));
  IList<IGamePiece> listShipBullet = new ConsList<IGamePiece>(shipBullet0,
      new ConsList<IGamePiece>(shipBullet1,
          new ConsList<IGamePiece>(shipBullet2, mt)));
  IList<IGamePiece> firedInvaderBullet = new ConsList<IGamePiece>(new InvaderBullet(7, 12), mt);
  IList<IGamePiece> appended = new ConsList<IGamePiece>(shipBullet0,
      new ConsList<IGamePiece>(shipBullet1,
          new ConsList<IGamePiece>(shipBullet2, listInvader)));
  IList<IGamePiece> outFrame = new ConsList<IGamePiece>(new ShipBullet(-50, -10), mt);
  IPred<IGamePiece> isHitting = new IsHitting(invader1);
  IPred<IGamePiece> listNotHitting = new ListNotHitting(listShipBullet);
  IPred<IGamePiece> fire = new Fire();
  IPred<IGamePiece> inFrame = new InFrame();
  IFunc1<IGamePiece, IGamePiece> update = new Update();
  IFunc1<IGamePiece, IGamePiece> fireInvaderBullet = new FireInvaderBullet();
  IFunc<IGamePiece, WorldScene, WorldScene> draw = new Draw();
  ShipWorld notForTesting = new ShipWorld();
  WorldScene background = this.notForTesting.getEmptyScene()
      .placeImageXY(new ScaleImage(new FromFileImage("background.png"), 1.2), 250, 250);

  WorldScene winScene = background.placeImageXY(new TextImage("You Beat the Invaders!!!", 20,
      FontStyle.BOLD_ITALIC, Color.white), 250, 250);

  WorldScene loseScene = background.placeImageXY(new TextImage("You Got Hit:(", 20,
      FontStyle.BOLD_ITALIC, Color.white), 250, 250);
  ShipWorld forTesting = new ShipWorld(ship1, listShipBullet, listInvader, firedInvaderBullet);

  WorldScene fullScene = firedInvaderBullet.fold(draw, listShipBullet.fold(draw,
      ship1.draw(listInvader.fold(draw, background))));

  // tests for MoveR and MoveL
  boolean testShipMove(Tester t) {
    return t.checkExpect(this.ship1.moveR(), ship2)
        && t.checkExpect(this.ship2.moveL(), ship1)
        && t.checkExpect(this.ship1.moveL(), ship0)
        && t.checkExpect(this.ship2.moveR(), ship15);
  }

  // test for draw
  boolean testDraw(Tester t) {
    return t.checkExpect(this.ship1.draw(this.empty), sceneShip1)
        && t.checkExpect(this.invader1.draw(this.empty), sceneInvader1)
        && t.checkExpect(this.shipBullet1.draw(this.empty), sceneShipBullet1)
        && t.checkExpect(this.invaderBullet1.draw(this.empty), sceneInvaderBullet1);
  }

  // tests for fireBullet
  boolean testFireBullet(Tester t) {
    return t.checkExpect(this.ship1.fireBullet(), ship1)
        && t.checkExpect(this.invader1.fireBullet(), invaderBullet1)
        && t.checkExpect(this.shipBullet1.fireBullet(), shipBullet1)
        && t.checkExpect(this.invaderBullet1.fireBullet(), invaderBullet1);
  }

  // tests for isFire
  boolean testIsFire(Tester t) {
    return t.checkExpect(this.ship1.isFire(), false)
        && t.checkExpect(this.invader1.isFire(), true)
        && t.checkExpect(this.invader2.isFire(), false)
        && t.checkExpect(this.shipBullet1.isFire(), false)
        && t.checkExpect(this.invaderBullet1.isFire(), false);
  }

  boolean testUpdate(Tester t) {
    return t.checkExpect(this.ship1.update(), ship1)
        && t.checkExpect(this.invader1.update(), new Invader(7, 7, 8, new Random()))
        && t.checkExpect(this.shipBullet1.update(), new ShipBullet(7, 28))
        && t.checkExpect(this.invaderBullet1.update(), new InvaderBullet(7, 13));
  }

  // tests for inFrame
  boolean testInFrame(Tester t) {
    return t.checkExpect(this.ship120.inFrame(), false)
        && t.checkExpect(this.ship1.inFrame(), true)
        && t.checkExpect(this.invader1.inFrame(), true)
        && t.checkExpect(this.shipBullet1.inFrame(), true)
        && t.checkExpect(this.shipBullet2.inFrame(), false)
        && t.checkExpect(this.invaderBullet1.inFrame(), true)
        && t.checkExpect(this.invaderBullet2.inFrame(), false);
  }

  // test for isHit
  boolean testIsHit(Tester t) {
    return t.checkExpect(this.ship120.isHit(0, 0), false)
        && t.checkExpect(this.ship1.isHit(5, 500), true)
        && t.checkExpect(this.invader1.isHit(7, 7), true)
        && t.checkExpect(this.invader2.isHit(7, 7), false)
        && t.checkExpect(this.shipBullet1.isHit(7, 25), true)
        && t.checkExpect(this.shipBullet2.isHit(100, 30), false)
        && t.checkExpect(this.invaderBullet2.isHit(2, 5), false);
  }

  // tests for isHitting
  boolean testIsHitting(Tester t) {
    return t.checkExpect(this.ship1.isHitting(invaderBullet1), false)
        && t.checkExpect(this.invader1.isHitting(shipBullet2), true)
        && t.checkExpect(this.invader2.isHitting(shipBullet1), false)
        && t.checkExpect(this.shipBullet2.isHitting(invader1), true)
        && t.checkExpect(this.shipBullet1.isHitting(invader1), false)
        && t.checkExpect(this.invaderBullet1.isHitting(ship1), false);
  }

  // tests for all predicates
  boolean testPred(Tester t) {
    return t.checkExpect(this.isHitting.apply(shipBullet1), false)
        && t.checkExpect(this.isHitting.apply(shipBullet2), true)
        && t.checkExpect(this.listNotHitting.apply(invader1), false)
        && t.checkExpect(this.listNotHitting.apply(invader2), true)
        && t.checkExpect(this.fire.apply(invader1), true)
        && t.checkExpect(this.fire.apply(invader2), false)
        && t.checkExpect(this.fire.apply(ship1), false)
        && t.checkExpect(this.inFrame.apply(shipBullet1), true)
        && t.checkExpect(this.inFrame.apply(shipBullet2), false)
        && t.checkExpect(this.inFrame.apply(invader2), true);
  }

  // tests for all functions with 1 input
  boolean testFunc1(Tester t) {
    return t.checkExpect(this.update.apply(shipBullet1), new ShipBullet(7, 28))
        && t.checkExpect(this.update.apply(ship1), ship1)
        && t.checkExpect(this.update.apply(invaderBullet1), new InvaderBullet(7, 13))
        && t.checkExpect(this.fireInvaderBullet.apply(invader1), new InvaderBullet(7, 12))
        && t.checkExpect(this.fireInvaderBullet.apply(invader2), new InvaderBullet(100, 105))
        && t.checkExpect(this.fireInvaderBullet.apply(ship1), ship1);
  }

  // tests for the draw function object
  boolean testDrawFunc(Tester t) {
    return t.checkExpect(this.draw.apply(ship1, empty), sceneShip1)
        && t.checkExpect(this.draw.apply(invader1, empty), sceneInvader1)
        && t.checkExpect(this.draw.apply(invaderBullet1, empty), sceneInvaderBullet1)
        && t.checkExpect(this.draw.apply(shipBullet1, empty), sceneShipBullet1);
  }

  // tests for fold
  boolean testFold(Tester t) {
    return t.checkExpect(listInvader.fold(draw, empty),
        this.invader0.draw(this.invader2.draw(sceneInvader1)))
        && t.checkExpect(mt.fold(draw, empty), empty);
  }

  // tests for map
  boolean testMap(Tester t) {
    return t
        .checkExpect(this.listShipBullet.map(update), new ConsList<IGamePiece>(shipBullet0.update(),
            new ConsList<IGamePiece>(shipBullet1.update(),
                new ConsList<IGamePiece>(shipBullet2.update(), mt))))
        && t.checkExpect(this.mt.map(update), mt);
  }

  // tests for condMap
  boolean testCondMap(Tester t) {
    return t.checkExpect(this.listInvader.condMap(fire, fireInvaderBullet), firedInvaderBullet)
        && t.checkExpect(this.mt.condMap(fire, update), mt);
  }

  // tests for count
  boolean testCount(Tester t) {
    return t.checkExpect(this.listInvader.count(), 3)
        && t.checkExpect(this.mt.count(), 0);
  }

  // tests for filter
  boolean testFilter(Tester t) {
    return t.checkExpect(this.listShipBullet.filter(inFrame), new ConsList<IGamePiece>(shipBullet0,
        new ConsList<IGamePiece>(shipBullet1, mt)))
        && t.checkExpect(this.mt.filter(inFrame), mt);
  }

  // tests for orMap
  boolean testOrMap(Tester t) {
    return t.checkExpect(this.outFrame.ormap(inFrame), false)
        && t.checkExpect(this.listInvader.ormap(inFrame), true)
        && t.checkExpect(this.mt.ormap(inFrame), false);
  }

  // tests for append
  boolean testAppend(Tester t) {
    return t.checkExpect(this.listShipBullet.append(listInvader), appended)
        && t.checkExpect(this.mt.append(appended), appended);
  }

  // tests for make Scenes
  boolean testScenes(Tester t) {
    return t.checkExpect(notForTesting.makeWinScene(), winScene)
        && t.checkExpect(notForTesting.makeLoseScene(), loseScene)
        && t.checkExpect(forTesting.makeScene(), fullScene);
  }

  // tests for onKeyEvent
  boolean testOnKey(Tester t) {
    return t.checkExpect(forTesting.onKeyEvent(" "),
        new ShipWorld(ship1, listShipBullet, listInvader, firedInvaderBullet))
        && t.checkExpect(forTesting.onKeyEvent("right"),
            new ShipWorld(ship2, listShipBullet, listInvader, firedInvaderBullet))
        && t.checkExpect(forTesting.onKeyEvent("left"),
            new ShipWorld(new Spaceship(0), listShipBullet, listInvader, firedInvaderBullet))
        && t.checkExpect(forTesting.onKeyEvent("a"), forTesting);
  }

  boolean testGo(Tester t) {
    // create my world. This will initialize everything
    ShipWorld myWorld = new ShipWorld();

    // Start the game with a big bang. The third argument is the number of seconds
    // between ticks. So currently the game
    // progresses at 200 ticks/second, which is probably higher than what is
    // supported by the frame rate.
    return myWorld.bigBang(500, 500, 0.005);
  }
}