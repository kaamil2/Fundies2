package HW.hw10;


import java.util.*;

import javalib.impworld.*;

import java.awt.Color;

import javalib.worldimages.*;

import tester.Tester;


// USER Information On How to Play the Game
// 1. Click on the "Run" button to start the game this will generate the randomly genreated
// maze we will then
//   be able to solve the maze by pressing one of the three buttons "b" for breadth-first Search,
//   "d" for depth-first search, or "m" for allowing the user to move around the maze and solve it.
// 2. Once the maze is generated the user can press the "b" button to solve the maze using
// breadth-first search
//   or the "d" button to solve the maze using depth-first search. if you start the breadth
//   first searh and want to
// look at the depth first search you can press the "d" button and it will switch the maze
// showing depth-first search.
// if you want to toggle from the searched maze and the path of the maze press the "t" button.
// 3. If you want to move around the maze and solve it yourself you can press the "m" button
// and then use the arrow
// keys to move around the maze.
// 4. If you want to generate a new maze you can press the "r" button and it will generate
// a new maze.
//  at any point in the game for larger mazes it will take a few seconds to generate the maze.
// the score is being tracked and a point will be awarded to the fastest search algorithm.
// but it must be ran to
// completion to be awarded the point. and the point will only be shown when the user resets
// the maze. to try and
// seeing which search algorithm is faster again.


interface IList<T> extends Iterable<T> {

  // returns true if this list is empty
  boolean isEmpty();

  // returns the length of this list
  int length();

  // adds the given element to the end of this list
  IList<T> add(T t);

  // returns the element at the given index
  T get(int i);

  // returns true if this list has a next element
  boolean hasNext();

  // returns the next element in this list
  T getData();

  // returns the next element in this list
  IList<T> next();
}


class Vertex {
  int x;
  int y;
  boolean visited;
  boolean isPath;
  ArrayList<Edge> mazeEdges;
  Color color;

  Vertex(int x, int y) {
    this.x = x;
    this.y = y;
    this.mazeEdges = new ArrayList<>();
    this.visited = false;
    this.isPath = false;
    this.color = Color.BLACK;
  }


  // returns the vertex number of this vertex
  int vertexNum() {
    return 1000 * y + x;
  }


  public boolean equals(Object o) {
    if (o instanceof Vertex) {
      Vertex that = (Vertex) o;
      return this.x == that.x
              && this.y == that.y
              && this.visited == that.visited
              && this.isPath == that.isPath
              && this.color == that.color
              && this.mazeEdges.equals(that.mazeEdges)
              && this.vertexNum() == that.vertexNum();
    } else {
      return false;
    }
  }

  public int hashCode() {
    return this.vertexNum();
  }

}


class Edge {
  Vertex from;
  Vertex to;
  int weight;

  Edge(Vertex from, Vertex to, int weight) {
    this.from = from;
    this.to = to;
    this.weight = weight;
  }
}


class MtLo<T> implements IList<T>, Iterable<T> {

  public int length() {
    return 0;
  }

  public boolean isEmpty() {
    return true;
  }

  public IList<T> add(T t) {
    return new ConsLo<>(t, new MtLo<>());
  }

  public T get(int i) {
    return null;
  }

  public boolean hasNext() {
    return false;
  }

  public Iterator<T> iterator() {
    return new ListIterator<>(this);
  }

  public T getData() {
    return null;
  }

  public IList<T> next() {
    throw new NoSuchElementException("No next for empty list");
  }
}

class ConsLo<T> implements IList<T>, Iterable<T> {
  T first;
  IList<T> rest;

  ConsLo(T first, IList<T> rest) {
    this.first = first;
    this.rest = rest;
  }

  public int length() {
    return 1 + this.rest.length();
  }

  public boolean isEmpty() {
    return false;
  }

  public Iterator<T> iterator() {
    return new ListIterator<>(this);
  }

  public IList<T> add(T t) {
    if (rest.isEmpty()) {
      rest = new ConsLo<>(t, new MtLo<>());
    } else {
      rest = rest.add(t);
    }
    return this;
  }

  public T get(int i) {
    if (i == 0) {
      return this.first;
    } else {
      return this.rest.get(i - 1);
    }
  }

  public boolean hasNext() {
    return true;
  }

  public T getData() {
    return this.first;
  }

  public IList<T> next() {
    return this.rest;
  }
}

class ListIterator<T> implements Iterator<T> {
  IList<T> curr;

  ListIterator(IList<T> curr) {
    this.curr = curr;
  }

  public boolean hasNext() {
    return curr.hasNext();
  }

  public T next() {
    T temp = this.curr.getData();
    this.curr = this.curr.next();
    return temp;
  }


}

interface IComparator<T> {
  boolean apply(T t1, T t2);
}


class CompareEdges implements IComparator<Edge> {
  public boolean apply(Edge e1, Edge e2) {
    return e1.weight < e2.weight;
  }
}

// now i will create a BFS and DFS algorithm to find the shortest path from the start to the end
// i will use the maze i created in the previous assignment to test my algorithms on it and see if
// they work properly or not and if they do then i will use them to find the shortest path in the
// maze i created in this assignment and then i will use the shortest path to display
// the shortest path and the path the BFS and DFS algorithms took to find the shortest path
// in the maze i created in this assignment


class Maze extends World {


  int width;
  IList<Vertex> vertices;
  int height;
  boolean solvedUser;
  boolean solvedMaze;
  boolean solvedBFS;
  ArrayList<Vertex> visited;
  ArrayList<Vertex> path;
  HashMap<Integer, Integer> hash;
  IList<Edge> walls;
  ArrayList<ArrayList<Vertex>> mazeVertex;
  ArrayList<Edge> allEdges;
  List<Edge> paths;
  HashMap<String, Edge> cameFrom;
  HashMap<String, Edge> cameFromBFS;
  Queue<Vertex> work;
  Vertex endCurrent;

  Vertex endCurrentBFS;
  Stack<Vertex> workList;
  boolean dfsPressed;
  int wrongMovesCount;
  int wrongMovesBFS;
  int rightMovesCount;
  int rightMovesBFS;
  int dfsWins;
  boolean pathDrawnBFS;
  ArrayList<Vertex> visitedBFS;
  int bfsWins;
  boolean userPressedUp;
  boolean userPressedDown;
  boolean userPressedLeft;
  boolean userPressedRight;
  Vertex userStart;
  boolean bfsPressed;
  ArrayList<Vertex> pathUser;
  HashSet<Vertex> searching;
  List<Edge> pathBFS;
  boolean toggle;
  List<Edge> pathsUser;
  boolean userTurn;
  boolean pathDrawn;
  static int scale = 10;


  Maze(int width, int height) {
    this.height = height;
    this.width = width;
    this.mazeVertex = getVertex();
    this.allEdges = new ArrayList<>();
    this.userStart = mazeVertex.get(0).get(0);

    for (int i = 0; i < mazeVertex.size(); i++) {
      for (int j = 0; j < mazeVertex.get(i).size(); j++) {
        this.allEdges.addAll(mazeVertex.get(i).get(j).mazeEdges);
      }
    }

    this.mazeVertex = kruskalAlg(mazeVertex);
    this.walls = makeOutOfBounds(mazeVertex, allEdges);
    this.vertices = new MtLo<>();
    this.visited = new ArrayList<>();
    this.path = new ArrayList<>();
    this.solvedMaze = false;
    this.solvedBFS = false;
    this.workList = new Stack<>();
    this.visitedBFS = new ArrayList<>();
    this.workList.push(mazeVertex.get(0).get(0));
    this.work = new LinkedList<>();
    this.work.add(mazeVertex.get(0).get(0));
    this.searching = new HashSet<>();
    this.pathUser = new ArrayList<>();
    this.paths = new ArrayList<>();
    this.toggle = false;
    this.pathBFS = new ArrayList<>();
    this.endCurrentBFS = mazeVertex.get(mazeVertex.size() - 1).get(mazeVertex.get(0).size() - 1);
    this.pathDrawn = false;
    this.cameFrom = new HashMap<>();
    this.cameFromBFS = new HashMap<>();
    this.dfsPressed = false;
    this.bfsPressed = false;
    this.pathDrawnBFS = false;
    this.userPressedUp = false;
    this.userPressedDown = false;
    this.userPressedLeft = false;
    this.userPressedRight = false;
    this.solvedUser = false;
    this.userTurn = false;
    this.pathsUser = new ArrayList<>();
    this.wrongMovesBFS = 0;
    this.rightMovesBFS = 0;
    this.wrongMovesCount = 0;
    this.bfsWins = 0;
    this.dfsWins = 0;
    this.rightMovesCount = 0;
    this.endCurrent = mazeVertex.get(mazeVertex.size() - 1).get(mazeVertex.get(0).size() - 1);
    for (ArrayList<Vertex> allVertex : mazeVertex) {
      for (Vertex v : allVertex) {
        vertices = vertices.add(v);
      }
    }

    this.bigBang((width * scale) + 150, (height * scale) + 150, 0.01);

  }

  int kruskalHelpFind(HashMap<Integer, Integer> hashmap, int x) {
    if (hashmap.get(x) == x) {
      return x;
    } else {
      return kruskalHelpFind(hashmap, hashmap.get(x));
    }
  }

  // returns the maze with the kruskal algorithm applied
  ArrayList<ArrayList<Vertex>> kruskalAlg(ArrayList<ArrayList<Vertex>> vertex) {
    for (ArrayList<Vertex> i : vertex) {
      for (Vertex j : i) {
        j.mazeEdges = new ArrayList<>();
      }
    }
    ArrayList<Edge> allEdgesSorted = sort(allEdges);
    this.hash = new HashMap<>();
    IList<Edge> work = new MtLo<>();
    int cells = height * width;
    for (int i = 0; i <= (height * 2000) * width; i++) {
      hash.put(i, i);
    }
    while (work.length() < cells - 1) {
      Edge e = allEdgesSorted.get(0);
      if (this.kruskalHelpFind(hash, e.to.vertexNum())
              != this.kruskalHelpFind(hash, e.from.vertexNum())) {
        work = work.add(e);
        e.from.mazeEdges.add(e);
        e.to.mazeEdges.add(new Edge(e.to, e.from, e.weight));
        int hashVar = (kruskalHelpFind(hash, e.to.vertexNum()));
        hash.remove(kruskalHelpFind(hash, e.to.vertexNum()));
        hash.put(hashVar, kruskalHelpFind(hash, e.from.vertexNum()));
      }
      allEdgesSorted.remove(0);
    }
    return vertex;
  }

  // returns this maze's vertices
  ArrayList<ArrayList<Vertex>> getVertex() {
    ArrayList<ArrayList<Vertex>> vertices = new ArrayList<>();
    for (int x = 0; x < width; x++) {
      ArrayList<Vertex> v = new ArrayList<>();
      for (int y = 0; y < height; y++) {
        v.add(new Vertex(x, y));
      }
      vertices.add(v);
    }
    Random rand = new Random();
    for (ArrayList<Vertex> vList : vertices) {
      for (Vertex vertex : vList) {
        if (vertex.x != width - 1) {
          vertex.mazeEdges.add(new Edge(vertex, vertices.get(vertex.x + 1).get(vertex.y),
                  rand.nextInt(height * width * scale)));
        }
        if (vertex.x != 0) {
          vertex.mazeEdges.add(new Edge(vertex, vertices.get(vertex.x - 1).get(vertex.y),
                  rand.nextInt(height * width * scale)));
        }
        if (vertex.y != height - 1) {
          vertex.mazeEdges.add(new Edge(vertex, vertices.get(vertex.x).get(vertex.y + 1),
                  rand.nextInt(height * width * scale)));
        }
        if (vertex.y != 0) {
          vertex.mazeEdges.add(new Edge(vertex, vertices.get(vertex.x).get(vertex.y - 1),
                  rand.nextInt(height * width * scale)));
        }
        if (vertex.x == 0 && vertex.y == 0) {
          vertex.mazeEdges.add(new Edge(vertex, vertices.get(vertex.x + 1).get(vertex.y),
                  rand.nextInt(height * width * scale)));
          vertex.mazeEdges.add(new Edge(vertex, vertices.get(vertex.x).get(vertex.y + 1),
                  rand.nextInt(height * width * scale)));
        }
        if (vertex.x == width - 1 && vertex.y == height - 1) {
          vertex.mazeEdges.add(new Edge(vertex, vertices.get(vertex.x - 1).get(vertex.y),
                  rand.nextInt(height * width * scale)));
          vertex.mazeEdges.add(new Edge(vertex, vertices.get(vertex.x).get(vertex.y - 1),
                  rand.nextInt(height * width * scale)));
        }
        if (vertex.x == 0 && vertex.y == height - 1) {
          vertex.mazeEdges.add(new Edge(vertex, vertices.get(vertex.x + 1).get(vertex.y),
                  rand.nextInt(height * width * scale)));
          vertex.mazeEdges.add(new Edge(vertex, vertices.get(vertex.x).get(vertex.y - 1),
                  rand.nextInt(height * width * scale)));
        }
        if (vertex.x == width - 1 && vertex.y == 0) {
          vertex.mazeEdges.add(new Edge(vertex, vertices.get(vertex.x - 1).get(vertex.y),
                  rand.nextInt(height * width * scale)));
          vertex.mazeEdges.add(new Edge(vertex, vertices.get(vertex.x).get(vertex.y + 1),
                  rand.nextInt(height * width * scale)));
        }

      }
    }
    return vertices;
  }

  // combines two lists of edges
  ArrayList<Edge> combine(ArrayList<Edge> edgeList, ArrayList<Edge> edgeList2) {
    ArrayList<Edge> combinedEdges = new ArrayList<>();
    IComparator<Edge> compareEdges = new CompareEdges();
    int i = 0;
    int j = 0;
    while (i < edgeList.size() && j < edgeList2.size()) {
      if (compareEdges.apply(edgeList.get(i), edgeList2.get(j))) {
        combinedEdges.add(edgeList.get(i));
        i++;
      } else {
        combinedEdges.add(edgeList2.get(j));
        j++;
      }
    }
    while (i < edgeList.size()) {
      combinedEdges.add(edgeList.get(i));
      i++;
    }
    while (j < edgeList2.size()) {
      combinedEdges.add(edgeList2.get(j));
      j++;
    }
    return combinedEdges;
  }

  IList<Edge> makeOutOfBounds(ArrayList<ArrayList<Vertex>> vertex, ArrayList<Edge> allEdges) {
    IList<Edge> mt = new MtLo<>();
    for (int p = 0; p < allEdges.size(); p++) {
      boolean bool = true;
      for (int i = 0; i < vertex.size(); i++) {
        ArrayList<Vertex> l = vertex.get(i);
        for (int q = 0; q < l.size(); q++) {
          for (int k = 0; k < l.get(q).mazeEdges.size(); k++) {
            Edge e2 = l.get(q).mazeEdges.get(k);
            if (allEdges.get(p).equals(e2) || (allEdges.get(p).to
                    == e2.from && allEdges.get(p).from == e2.to)) {
              bool = false;
              break;
            }
          }
        }
      }
      if (bool) {
        mt = mt.add(allEdges.get(p));
      }
    }
    return mt;
  }

  // sorts a list of edges
  ArrayList<Edge> sort(ArrayList<Edge> l) {
    if (l.size() < 2) {
      return l;
    }
    ArrayList<Edge> edgeList = new ArrayList<>();
    ArrayList<Edge> edgeList2 = new ArrayList<>();
    int midddle = l.size() / 2;
    for (int i = 0; i < midddle; i++) {
      edgeList.add(l.get(i));
    }
    for (int i = midddle; i < l.size(); i++) {
      edgeList2.add(l.get(i));
    }
    edgeList = sort(edgeList);
    edgeList2 = sort(edgeList2);
    return combine(edgeList, edgeList2);
  }


  // onKeyPressed

  public void onKeyReleased(String key) {
    if (key.equals("t")) {
      toggle = false;
    }
  }

  public void onKeyEvent(String key) {
    if (key.equals("b")) {
      bfsPressed = true;
      dfsPressed = false;
      userTurn = false;
    }
    if (key.equals("d")) {
      dfsPressed = true;
      bfsPressed = false;
      userTurn = false;
    }

    if (key.equals("t")) {
      toggle = true;
    }

    if (key.equals("r")) {
      reset();
    }
    if (key.equals("m")) {
      userTurn = true;
      bfsPressed = false;
      dfsPressed = false;
    }
    if (key.equals("up") && userTurn) {
      userPressedUp = true;
      userPressedDown = false;
      userPressedLeft = false;
      userPressedRight = false;

    }
    if (key.equals("down") && userTurn) {
      userPressedDown = true;
      userPressedUp = false;
      userPressedLeft = false;
      userPressedRight = false;
    }
    if (key.equals("left") && userTurn) {
      userPressedLeft = true;
      userPressedDown = false;
      userPressedUp = false;
      userPressedRight = false;
    }
    if (key.equals("right") && userTurn) {
      userPressedRight = true;
      userPressedDown = false;
      userPressedLeft = false;
      userPressedUp = false;
    }


  }

  public void reset() {
    if (pathDrawn && pathDrawnBFS) {
      if (wrongMovesCount < wrongMovesBFS) {
        this.dfsWins = dfsWins + 1;
      }
      if (wrongMovesBFS < wrongMovesCount) {
        this.bfsWins = bfsWins + 1;
      }

    } else if (pathDrawn) {
      this.dfsWins = dfsWins + 1;
    } else if (pathDrawnBFS) {
      this.bfsWins = bfsWins + 1;
    } else {
      this.dfsWins = dfsWins;
      this.bfsWins = bfsWins;
    }
    this.width = width;
    this.mazeVertex = getVertex();
    this.allEdges = new ArrayList<>();
    this.userStart = mazeVertex.get(0).get(0);

    for (int i = 0; i < mazeVertex.size(); i++) {
      for (int j = 0; j < mazeVertex.get(i).size(); j++) {
        this.allEdges.addAll(mazeVertex.get(i).get(j).mazeEdges);
      }
    }

    this.mazeVertex = kruskalAlg(mazeVertex);
    this.walls = makeOutOfBounds(mazeVertex, allEdges);
    this.vertices = new MtLo<>();
    this.visited = new ArrayList<>();
    this.path = new ArrayList<>();
    this.solvedMaze = false;
    this.solvedBFS = false;
    this.workList = new Stack<>();
    this.visitedBFS = new ArrayList<>();
    this.workList.push(mazeVertex.get(0).get(0));
    this.work = new LinkedList<>();
    this.work.add(mazeVertex.get(0).get(0));
    this.searching = new HashSet<>();
    this.pathUser = new ArrayList<>();
    this.paths = new ArrayList<>();
    this.pathBFS = new ArrayList<>();
    this.endCurrentBFS = mazeVertex.get(mazeVertex.size() - 1).get(mazeVertex.get(0).size() - 1);
    this.pathDrawn = false;
    this.cameFrom = new HashMap<>();
    this.cameFromBFS = new HashMap<>();
    this.dfsPressed = false;
    this.bfsPressed = false;
    this.pathDrawnBFS = false;
    this.userPressedUp = false;
    this.userPressedDown = false;
    this.userPressedLeft = false;
    this.userPressedRight = false;
    this.solvedUser = false;
    this.userTurn = false;
    this.pathsUser = new ArrayList<>();
    this.wrongMovesBFS = 0;
    this.rightMovesBFS = 0;
    this.wrongMovesCount = 0;
    this.rightMovesCount = 0;
    this.endCurrent = mazeVertex.get(mazeVertex.size() - 1).get(mazeVertex.get(0).size() - 1);
    for (ArrayList<Vertex> allVertex : mazeVertex) {
      for (Vertex v : allVertex) {
        vertices = vertices.add(v);
      }
    }


  }

  public void reconstruct(HashMap<String, Edge> cameFrom) {
    if (endCurrent != mazeVertex.get(0).get(0)) {
      Edge e = cameFrom.get(endCurrent.toString());
      paths.add(e);
      endCurrent = e.from;
      rightMovesCount++;
      System.out.println(paths.size());
    }

  }

  public void reconstructBFS(HashMap<String, Edge> cameFromBFS) {
    if (endCurrentBFS != mazeVertex.get(0).get(0)) {
      Edge e = cameFromBFS.get(endCurrentBFS.toString());
      pathBFS.add(e);
      endCurrentBFS = e.from;
      rightMovesBFS++;
      System.out.println(pathBFS.size());
    }

  }

  public void manualMode() {
    if (userTurn) {
      if (userPressedUp) {
        for (int i = 0; i < mazeVertex.size(); i++) {
          for (int j = 0; j < mazeVertex.get(0).size(); j++) {
            if (mazeVertex.get(i).get(j).x == userStart.x
                    && mazeVertex.get(i).get(j).y == userStart.y) {
              if (j == 0) {
                return;
              }
              for (Edge e : mazeVertex.get(i).get(j).mazeEdges) {
                if (e.to.equals(mazeVertex.get(i).get(j - 1))) {
                  this.pathUser.add(mazeVertex.get(i).get(j));
                  userStart = mazeVertex.get(i).get(j - 1);
                  if (userStart.equals(mazeVertex.get(mazeVertex.size() - 1)
                          .get(mazeVertex.get(0).size() - 1))) {
                    solvedUser = true;
                    System.out.println("You win!");

                  }
                  userPressedLeft = false;
                  return;
                }
              }


            }


          }
        }
      }
      if (userPressedDown) {
        for (int i = 0; i < mazeVertex.size(); i++) {
          for (int j = 0; j < mazeVertex.get(0).size(); j++) {
            if (mazeVertex.get(i).get(j).x == userStart.x
                    && mazeVertex.get(i).get(j).y == userStart.y) {
              if (j == mazeVertex.get(0).size() - 1) {
                System.out.println("You can't go down");
                return;
              }
              for (Edge e : mazeVertex.get(i).get(j).mazeEdges) {
                if (e.to.equals(mazeVertex.get(i).get(j + 1))) {
                  this.pathUser.add(mazeVertex.get(i).get(j));
                  userStart = mazeVertex.get(i).get(j + 1);
                  if (userStart.equals(mazeVertex.get(mazeVertex.size() - 1)
                          .get(mazeVertex.get(0).size() - 1))) {
                    solvedUser = true;
                    System.out.println("You win!");

                  }
                  userPressedDown = false;
                  return;
                }

              }


            }

          }
        }
      }


      if (userPressedLeft) {
        for (int i = 0; i < mazeVertex.size(); i++) {
          for (int j = 0; j < mazeVertex.get(0).size(); j++) {
            if (mazeVertex.get(i).get(j).x == userStart.x
                    && mazeVertex.get(i).get(j).y == userStart.y) {
              if (i == 0) {
                return;
              }
              for (Edge e : mazeVertex.get(i).get(j).mazeEdges) {
                if (e.to.equals(mazeVertex.get(i - 1).get(j))) {
                  this.pathUser.add(mazeVertex.get(i).get(j));
                  userStart = mazeVertex.get(i - 1).get(j);
                  if (userStart.equals(mazeVertex.get(mazeVertex.size() - 1)
                          .get(mazeVertex.get(0).size() - 1))) {
                    solvedUser = true;
                    System.out.println("You win!");

                  }
                  userPressedLeft = false;
                  return;
                }
              }
            }
          }
        }
      }
      if (userPressedRight) {
        for (int i = 0; i < mazeVertex.size(); i++) {
          for (int j = 0; j < mazeVertex.get(0).size(); j++) {
            if (mazeVertex.get(i).get(j).x == userStart.x
                    && mazeVertex.get(i).get(j).y == userStart.y) {
              if (i == mazeVertex.get(0).size() - 1) {
                return;
              }
              for (Edge e : mazeVertex.get(i).get(j).mazeEdges) {
                if (e.to.equals(mazeVertex.get(i + 1).get(j))) {
                  this.pathUser.add(mazeVertex.get(i).get(j));
                  userStart = mazeVertex.get(i + 1).get(j);
                  if (userStart.equals(mazeVertex.get(mazeVertex.size() - 1)
                          .get(mazeVertex.get(0).size() - 1))) {
                    solvedUser = true;
                    System.out.println("You win!");

                  }
                  userPressedRight = false;
                  return;
                }

              }
            }
          }
        }
      }


    }


  }


  // bfs
  public void bfs(Queue<Vertex> workList) {
    if (!solvedBFS) {
      Vertex current = workList.poll();
      if (!visitedBFS.contains(current)) {
        visitedBFS.add(current);
        if (current.equals(mazeVertex.get(width - 1).get(height - 1))) {
          solvedBFS = true;
        } else {
          for (Edge e : current.mazeEdges) {
            if (!visitedBFS.contains(e.to)) {
              wrongMovesBFS++;
              workList.offer(e.to);
              cameFromBFS.put(e.to.toString(), e);

            }
          }
        }
      }
    }
  }


  // dfs
  public void dfs(Stack<Vertex> workList) {
    //workList.push(start);
    if (!solvedMaze) {


      Vertex current = workList.pop();
      if (!visited.contains(current)) {
        visited.add(current);
        if (current.equals(mazeVertex.get(width - 1).get(height - 1))) {
          solvedMaze = true;
          //reconstruct(cameFrom);
        } else {
          for (Edge e : current.mazeEdges) {
            if (!visited.contains(e.to)) {
              wrongMovesCount++;
              workList.push(e.to);
              cameFrom.put(e.to.toString(), e);

            }
          }
        }
      }
    }

  }

  // onTick

  public void onTick() {

    if (dfsPressed) {
      dfs(workList);
      bfs(work);
      if (solvedMaze && !pathDrawn) {
        reconstruct(cameFrom);

      }
      if (solvedBFS) {
        reconstructBFS(cameFromBFS);
      }
    }

    if (bfsPressed) {
      bfs(work);
      dfs(workList);
      if (solvedBFS) {
        reconstructBFS(cameFromBFS);

      }
      if (solvedMaze && !pathDrawn) {
        reconstruct(cameFrom);
      }
    }

    if (userTurn) {
      manualMode();
    }


  }

  // makes the scene for this maze
  public WorldScene makeScene() {
    WorldScene world = new WorldScene(width * scale, height * scale);
    if (!toggle) {
      for (Vertex vertex : vertices) {
        Color color;
        if (vertex.x == 0 && vertex.y == 0) {
          color = Color.blue;
        } else if (vertex.x == width - 1 && vertex.y == height - 1) {
          color = Color.red;
        } else {
          color = Color.white;
        }
        if (dfsPressed) {
          if (visited.contains(vertex)) {
            color = Color.yellow;
          }
          if (paths.size() > 0) {
            for (int i = 0; i < vertex.mazeEdges.size(); i++) {
              if (solvedMaze && (paths.contains(vertex.mazeEdges.get(i)))) {
                color = Color.green;
                if (vertex.x == 0 && vertex.y == 0) {
                  pathDrawn = true;
                }
              }
            }
          }
        }

        if (bfsPressed) {
          if (visitedBFS.contains(vertex)) {
            color = Color.PINK;
          }
          if (pathBFS.size() > 0) {
            for (int j = 0; j < vertex.mazeEdges.size(); j++) {
              if (solvedBFS && (pathBFS.contains(vertex.mazeEdges.get(j)))) {
                color = Color.black;
                if (vertex.x == 0 && vertex.y == 0) {
                  pathDrawnBFS = true;
                }
              }
            }
          }
        }


        world.placeImageXY(new RectangleImage(scale, scale, OutlineMode.SOLID, color),
                (vertex.x * scale) + (scale / 2), (vertex.y * scale) + (scale / 2));
      }


      for (Edge e : walls) {
        if (e.to.x != e.from.x) {
          world.placeImageXY(
                  new RectangleImage(scale / 10, scale, OutlineMode.SOLID, Color.black),
                  ((e.to.x + e.from.x) * scale / 2) + (scale / 2),
                  (e.to.y * scale) + (scale / 2));

        } else {
          world.placeImageXY(
                  new RectangleImage(scale, scale / 10, OutlineMode.SOLID, Color.black),
                  (e.to.x * scale) + (scale / 2),
                  ((e.to.y + e.from.y) * scale / 2) + (scale / 2));

        }
      }

      if (userTurn) {
        world.placeImageXY(
                new RectangleImage(scale, scale, OutlineMode.SOLID, Color.orange),
                (userStart.x * scale) + (scale / 2),
                (userStart.y * scale) + (scale / 2));
        world.placeImageXY(new TextImage("User", 10, Color.black),
                ((mazeVertex.get(mazeVertex.size() - 1)
                        .get(mazeVertex.get(mazeVertex.size() - 1).size() - 1).x
                        * scale) + 130),
                (mazeVertex.get(mazeVertex.size() - 1)
                        .get(mazeVertex.get(mazeVertex.size() - 1).size() - 1).y
                        * scale) - 50);
        if (solvedUser) {

          for (int j = 0; j < pathUser.size(); j++) {
            world.placeImageXY(
                    new RectangleImage(scale, scale, OutlineMode.SOLID, Color.MAGENTA),
                    (pathUser.get(j).x * scale) + (scale / 2),
                    (pathUser.get(j).y * scale) + (scale / 2));

          }
        }
      }
    }

    if (toggle) {

      for (Vertex vertex : vertices) {
        Color color;
        if (vertex.x == 0 && vertex.y == 0) {
          color = Color.blue;
        } else if (vertex.x == width - 1 && vertex.y == height - 1) {
          color = Color.red;
        } else {
          color = Color.white;
        }
        if (dfsPressed) {
          if (paths.size() > 0) {
            for (int i = 0; i < vertex.mazeEdges.size(); i++) {
              if (solvedMaze && (paths.contains(vertex.mazeEdges.get(i)))) {
                color = Color.green;
                if (vertex.x == 0 && vertex.y == 0) {
                  pathDrawn = true;
                }
              }
            }
          }
        }

        if (bfsPressed) {
          if (pathBFS.size() > 0) {
            for (int j = 0; j < vertex.mazeEdges.size(); j++) {
              if (solvedBFS && (pathBFS.contains(vertex.mazeEdges.get(j)))) {
                color = Color.black;
                if (vertex.x == 0 && vertex.y == 0) {
                  pathDrawnBFS = true;
                }
              }
            }
          }
        }


        world.placeImageXY(new RectangleImage(scale, scale, OutlineMode.SOLID, color),
                (vertex.x * scale) + (scale / 2), (vertex.y * scale) + (scale / 2));
      }


      for (Edge e : walls) {
        if (e.to.x != e.from.x) {
          world.placeImageXY(
                  new RectangleImage(scale / 10, scale, OutlineMode.SOLID, Color.black),
                  ((e.to.x + e.from.x) * scale / 2) + (scale / 2),
                  (e.to.y * scale) + (scale / 2));

        } else {
          world.placeImageXY(
                  new RectangleImage(scale, scale / 10, OutlineMode.SOLID, Color.black),
                  (e.to.x * scale) + (scale / 2),
                  ((e.to.y + e.from.y) * scale / 2) + (scale / 2));

        }
      }

    }


    if (dfsPressed) {
      world.placeImageXY(new TextImage("" + wrongMovesCount,
                      10, Color.black),
              ((mazeVertex.get(mazeVertex.size() - 1)
                      .get(mazeVertex.get(mazeVertex.size() - 1).size() - 1).x
                      * scale) + 130),
              (mazeVertex.get(mazeVertex.size() - 1)
                      .get(mazeVertex.get(mazeVertex.size() - 1).size() - 1).y
                      * scale));
      world.placeImageXY(new TextImage("" + rightMovesCount,
                      10, Color.black),
              ((mazeVertex.get(mazeVertex.size() - 1)
                      .get(mazeVertex.get(mazeVertex.size() - 1).size() - 1).x
                      * scale) + 130),
              (mazeVertex.get(mazeVertex.size() - 1)
                      .get(mazeVertex.get(mazeVertex.size() - 1).size() - 1).y
                      * scale) + 20);
      world.placeImageXY(new TextImage("DFS", 10, Color.black),
              ((mazeVertex.get(mazeVertex.size() - 1)
                      .get(mazeVertex.get(mazeVertex.size() - 1).size() - 1).x
                      * scale) + 130),
              (mazeVertex.get(mazeVertex.size() - 1)
                      .get(mazeVertex.get(mazeVertex.size() - 1).size() - 1).y
                      * scale) - 50);
      if (solvedMaze) {
        world.placeImageXY(new RectangleImage(scale, scale, OutlineMode.SOLID, Color.green),
                (mazeVertex.get(mazeVertex.size() - 1).get(mazeVertex.get(0).size() - 1).x * scale)
                        + (scale / 2),
                (mazeVertex.get(mazeVertex.size() - 1).get(mazeVertex.get(0).size() - 1).y * scale)
                        + (scale / 2));
      }
    }
    if (bfsPressed) {
      world.placeImageXY(new TextImage("" + wrongMovesBFS,
                      10, Color.black),
              ((mazeVertex.get(mazeVertex.size() - 1)
                      .get(mazeVertex.get(mazeVertex.size() - 1).size() - 1).x
                      * scale) + 130),
              (mazeVertex.get(mazeVertex.size() - 1)
                      .get(mazeVertex.get(mazeVertex.size() - 1).size() - 1).y
                      * scale));
      world.placeImageXY(new TextImage("" + rightMovesBFS,
                      10, Color.black),
              ((mazeVertex.get(mazeVertex.size() - 1)
                      .get(mazeVertex.get(mazeVertex.size() - 1).size() - 1).x
                      * scale) + 130),
              (mazeVertex.get(mazeVertex.size() - 1)
                      .get(mazeVertex.get(mazeVertex.size() - 1).size() - 1).y
                      * scale) + 20);
      world.placeImageXY(new TextImage("BFS", 10, Color.black),
              ((mazeVertex.get(mazeVertex.size() - 1)
                      .get(mazeVertex.get(mazeVertex.size() - 1).size() - 1).x
                      * scale) + 130),
              (mazeVertex.get(mazeVertex.size() - 1)
                      .get(mazeVertex.get(mazeVertex.size() - 1).size() - 1).y
                      * scale) - 50);
      if (solvedBFS) {
        world.placeImageXY(new RectangleImage(scale, scale, OutlineMode.SOLID, Color.black),
                (mazeVertex.get(mazeVertex.size() - 1).get(mazeVertex.get(0).size() - 1).x * scale)
                        + (scale / 2),
                (mazeVertex.get(mazeVertex.size() - 1).get(mazeVertex.get(0).size() - 1).y * scale)
                        + (scale / 2));
      }
    }
    world.placeImageXY(new TextImage("Wrong Moves Count: ",
                    10, Color.black),
            ((mazeVertex.get(mazeVertex.size() - 1)
                    .get(mazeVertex.get(mazeVertex.size() - 1).size() - 1).x
                    * scale)
                    + 65),
            (mazeVertex.get(mazeVertex.size() - 1)
                    .get(mazeVertex.get(mazeVertex.size() - 1).size() - 1).y
                    * scale));
    world.placeImageXY(new TextImage("Right Moves Count: ",
                    10, Color.black),
            ((mazeVertex.get(mazeVertex.size() - 1)
                    .get(mazeVertex.get(mazeVertex.size() - 1).size() - 1).x
                    * scale)
                    + 65),
            (mazeVertex.get(mazeVertex.size() - 1)
                    .get(mazeVertex.get(mazeVertex.size() - 1).size() - 1).y
                    * scale)
                    + 20);

    world.placeImageXY(new TextImage("BFS Wins: " + bfsWins,
                    10, Color.black),
            ((mazeVertex.get(mazeVertex.size() - 1)
                    .get(mazeVertex.get(mazeVertex.size() - 1).size() - 1).x
                    * scale)
                    + 50),
            (mazeVertex.get(mazeVertex.size() - 1)
                    .get(mazeVertex.get(mazeVertex.size() - 1).size() - 1).y
                    * scale)
                    + 40);
    world.placeImageXY(new TextImage("DFS Wins: " + dfsWins,
                    10, Color.black),
            ((mazeVertex.get(mazeVertex.size() - 1)
                    .get(mazeVertex.get(mazeVertex.size() - 1).size() - 1).x
                    * scale)
                    + 110),
            (mazeVertex.get(mazeVertex.size() - 1)
                    .get(mazeVertex.get(mazeVertex.size() - 1).size() - 1).y
                    * scale)
                    + 40);
    world.placeImageXY(new TextImage("To Reset Press R ",
                    10, Color.black),
            ((mazeVertex.get(mazeVertex.size() - 1)
                    .get(mazeVertex.get(mazeVertex.size() - 1).size() - 1).x
                    * scale)
                    + 100),
            (mazeVertex.get(mazeVertex.size() - 1)
                    .get(mazeVertex.get(mazeVertex.size() - 1).size() - 1).y
                    * scale) - 70);
    world.placeImageXY(new TextImage("To BFS Press B ",
                    10, Color.black),
            ((mazeVertex.get(mazeVertex.size() - 1)
                    .get(mazeVertex.get(mazeVertex.size() - 1).size() - 1).x
                    * scale)
                    + 100),
            (mazeVertex.get(mazeVertex.size() - 1)
                    .get(mazeVertex.get(mazeVertex.size() - 1).size() - 1).y
                    * scale) - 90);
    world.placeImageXY(new TextImage("To DFS Press D ",
                    10, Color.black),
            ((mazeVertex.get(mazeVertex.size() - 1)
                    .get(mazeVertex.get(mazeVertex.size() - 1).size() - 1).x
                    * scale)
                    + 100),
            (mazeVertex.get(mazeVertex.size() - 1)
                    .get(mazeVertex.get(mazeVertex.size() - 1).size() - 1).y
                    * scale) - 110);
    world.placeImageXY(new TextImage("To User Solve Press M ",
                    10, Color.black),
            ((mazeVertex.get(mazeVertex.size() - 1)
                    .get(mazeVertex.get(mazeVertex.size() - 1).size() - 1).x
                    * scale)
                    + 100),
            (mazeVertex.get(mazeVertex.size() - 1)
                    .get(mazeVertex.get(mazeVertex.size() - 1).size() - 1).y
                    * scale) - 130);
    world.placeImageXY(new TextImage("To Toggle Solved Path Press T ",
                    10, Color.black),
            ((mazeVertex.get(mazeVertex.size() - 1)
                    .get(mazeVertex.get(mazeVertex.size() - 1).size() - 1).x
                    * scale)
                    + 85),
            (mazeVertex.get(mazeVertex.size() - 1)
                    .get(mazeVertex.get(mazeVertex.size() - 1).size() - 1).y
                    * scale) - 150);
    world.placeImageXY(new TextImage("MAZE ",
                    20, Color.blue),
            ((mazeVertex.get(mazeVertex.size() - 1)
                    .get(mazeVertex.get(mazeVertex.size() - 1).size() - 1).x
                    * scale) - 110),
            (mazeVertex.get(mazeVertex.size() - 1)
                    .get(mazeVertex.get(mazeVertex.size() - 1).size() - 1).y
                    * scale) + 70);
    world.placeImageXY(new TextImage("SOLVER",
                    20, Color.red),
            ((mazeVertex.get(mazeVertex.size() - 1)
                    .get(mazeVertex.get(mazeVertex.size() - 1).size() - 1).x
                    * scale) - 40),
            (mazeVertex.get(mazeVertex.size() - 1)
                    .get(mazeVertex.get(mazeVertex.size() - 1).size() - 1).y
                    * scale) + 70);


    return world;
  }


}


class ExamplesMaze {

  Maze mSmall = new Maze(60, 70);

 /* Maze m = new Maze(60, 100);

  Maze mLarge = new Maze(80, 100);


  void testGetWalls(Tester t) {
    t.checkExpect(m.makeOutOfBounds(m.mazeVertex, m.allEdges), m.walls);
    t.checkExpect(mSmall.makeOutOfBounds(mSmall.mazeVertex, mSmall.allEdges), mSmall.walls);
    t.checkExpect(mLarge.makeOutOfBounds(mLarge.mazeVertex, mLarge.allEdges), mLarge.walls);

  }

  void testKruskalAlg(Tester t) {
    t.checkExpect(m.kruskalAlg(m.mazeVertex), m.mazeVertex);
    t.checkExpect(mSmall.kruskalAlg(mSmall.mazeVertex), mSmall.mazeVertex);
    t.checkExpect(mLarge.kruskalAlg(mLarge.mazeVertex), mLarge.mazeVertex);

  }

  void testMakeScene(Tester t) {
    t.checkExpect(m.makeScene(), m.makeScene());
    t.checkExpect(mSmall.makeScene(), mSmall.makeScene());
    t.checkExpect(mLarge.makeScene(), mLarge.makeScene());
  }

  public void testIsEmpty(Tester t) {
    t.checkExpect(new ConsLo<String>("Hello", new MtLo<>()).isEmpty(), false);
    t.checkExpect(new MtLo<String>().isEmpty(), true);
    t.checkExpect(new ConsLo<String>("Hello",
            new ConsLo<String>("World", new MtLo<String>())).isEmpty(), false);
  }

  public void testLen(Tester t) {
    t.checkExpect(new ConsLo<String>("Hello", new ConsLo<String>("World",
            new MtLo<String>())).length(), 2);
    t.checkExpect(new ConsLo<String>("Hello", new MtLo<String>()).length(), 1);
    t.checkExpect(new MtLo<String>().length(), 0);
  }

  public void testIterator(Tester t) {
    IList<String> list = new ConsLo<String>("Hello",
            new ConsLo<String>("World", new ConsLo<String>("!", new MtLo<String>())));
    Iterator<String> iter = list.iterator();
    t.checkExpect(iter.hasNext(), true);
    t.checkExpect(iter.next(), "Hello");
    t.checkExpect(iter.hasNext(), true);
    t.checkExpect(iter.next(), "World");
    t.checkExpect(iter.hasNext(), true);
    t.checkExpect(iter.next(), "!");
    t.checkExpect(iter.hasNext(), false);
    t.checkException(new NoSuchElementException("No next for empty list"), iter, "next");

  }

  public void testAdd(Tester t) {
    IList<String> list = new MtLo<String>();
    t.checkExpect(list.add("Hello"), new ConsLo<String>("Hello", new MtLo<String>()));
    t.checkExpect(list.add("Hello").add("World"),
            new ConsLo<String>("Hello", new ConsLo<String>("World", new MtLo<String>())));
    t.checkExpect(list.add("Hello").add("World").add("!"),
            new ConsLo<String>("Hello", new ConsLo<String>("World",
                    new ConsLo<String>("!", new MtLo<String>()))));
  }

  public void testGet(Tester t) {
    IList<String> list = new ConsLo<String>("Hello",
            new ConsLo<String>("World", new ConsLo<String>("!", new MtLo<String>())));
    t.checkExpect(list.get(0), "Hello");
    IList<String> emptyList = new MtLo<String>();
    t.checkExpect(emptyList.get(0), null);
  }

  public void testGetData(Tester t) {
    IList<String> list = new ConsLo<String>("Hello",
            new ConsLo<String>("World", new ConsLo<String>("!", new MtLo<String>())));
    t.checkExpect(list.getData(), "Hello");
    IList<String> emptyList = new MtLo<String>();
    t.checkExpect(emptyList.getData(), null);
  }

  public void testGetNext(Tester t) {
    IList<String> list = new ConsLo<String>("Hello",
            new ConsLo<String>("World", new ConsLo<String>("!", new MtLo<String>())));
    IList<String> list2 = new ConsLo<String>("Hello", new MtLo<String>());
    t.checkExpect(list.next(), new ConsLo<String>("World",
            new ConsLo<String>("!", new MtLo<String>())));
    t.checkExpect(list2.next(), new MtLo<String>());
  }

  public void testSort(Tester t) {
    ArrayList<Edge> edgeList = new ArrayList<>();
    ArrayList<Edge> unsortedEdgeList = new ArrayList<>();
    ArrayList<Edge> edgeList2 = new ArrayList<>();
    ArrayList<Edge> unsortedEdgeList2 = new ArrayList<>();
    ArrayList<Edge> edgeList3 = new ArrayList<>();
    ArrayList<Edge> unsortedEdgeList3 = new ArrayList<>();

    Edge e1 = new Edge(null, null, 1);
    Edge e2 = new Edge(null, null, 3);
    Edge e3 = new Edge(null, null, 4);
    Edge e4 = new Edge(null, null, 7);
    Edge e5 = new Edge(null, null, 2);

    edgeList2.add(e1);
    edgeList2.add(e2);
    edgeList2.add(e3);

    edgeList3.add(e1);
    edgeList3.add(e2);

    edgeList.add(e1);
    edgeList.add(e5);
    edgeList.add(e2);
    edgeList.add(e3);
    edgeList.add(e4);

    unsortedEdgeList.add(e1);
    unsortedEdgeList.add(e2);
    unsortedEdgeList.add(e3);
    unsortedEdgeList.add(e4);
    unsortedEdgeList.add(e5);

    unsortedEdgeList2.add(e1);
    unsortedEdgeList2.add(e3);
    unsortedEdgeList2.add(e2);

    unsortedEdgeList3.add(e2);
    unsortedEdgeList3.add(e1);


    t.checkExpect(m.sort(unsortedEdgeList), edgeList);
    t.checkExpect(m.sort(unsortedEdgeList2), edgeList2);
    t.checkExpect(m.sort(unsortedEdgeList3), edgeList3);
  }

  public void testCombine(Tester t) {

    ArrayList<Edge> edgeList = new ArrayList<>();
    ArrayList<Edge> edgeList4 = new ArrayList<>();
    ArrayList<Edge> edgeList2 = new ArrayList<>();
    ArrayList<Edge> edgeList5 = new ArrayList<>();
    ArrayList<Edge> edgeList3 = new ArrayList<>();
    ArrayList<Edge> edgeList6 = new ArrayList<>();
    ArrayList<Edge> edgeList7 = new ArrayList<>();
    ArrayList<Edge> edgeList8 = new ArrayList<>();
    ArrayList<Edge> edgeList9 = new ArrayList<>();

    Edge e1 = new Edge(null, null, 1);
    Edge e2 = new Edge(null, null, 3);
    Edge e3 = new Edge(null, null, 4);
    Edge e4 = new Edge(null, null, 7);
    Edge e5 = new Edge(null, null, 2);

    edgeList.add(e1);
    edgeList5.add(e2);

    edgeList6.add(e1);
    edgeList6.add(e2);


    edgeList2.add(e1);
    edgeList2.add(e2);
    edgeList3.add(e3);

    edgeList4.add(e1);
    edgeList4.add(e2);
    edgeList4.add(e3);

    edgeList7.add(e1);
    edgeList7.add(e2);
    edgeList7.add(e3);
    edgeList7.add(e4);
    edgeList8.add(e5);

    edgeList9.add(e1);
    edgeList9.add(e5);
    edgeList9.add(e2);
    edgeList9.add(e3);
    edgeList9.add(e4);


    t.checkExpect(m.combine(edgeList2, edgeList3), edgeList4);
    t.checkExpect(m.combine(edgeList, edgeList5), edgeList6);
    t.checkExpect(m.combine(edgeList7, edgeList8), edgeList9);

  }

  public void testKruskalHelpFind(Tester t) {
    HashMap<Integer, Integer> hashmap = new HashMap<Integer, Integer>();
    Maze maze2 = new Maze(10, 10);
    hashmap.put(1, 1);
    hashmap.put(2, 1);
    hashmap.put(3, 2);
    hashmap.put(4, 3);
    hashmap.put(5, 4);
    hashmap.put(6, 6);
    hashmap.put(7, 6);


    t.checkExpect(maze2.kruskalHelpFind(hashmap, 1), 1);
    t.checkExpect(maze2.kruskalHelpFind(hashmap, 2), 1);
    t.checkExpect(maze2.kruskalHelpFind(hashmap, 3), 1);
    t.checkExpect(maze2.kruskalHelpFind(hashmap, 4), 1);
    t.checkExpect(maze2.kruskalHelpFind(hashmap, 5), 1);
    t.checkExpect(maze2.kruskalHelpFind(hashmap, 6), 6);
    t.checkExpect(maze2.kruskalHelpFind(hashmap, 7), 6);
  }


  void testVertexNum(Tester t) {
    t.checkExpect(mSmall.mazeVertex.get(0).get(0).vertexNum(), 0);
    t.checkExpect(mSmall.mazeVertex.get(1).get(0).vertexNum(), 1);
    t.checkExpect(mSmall.mazeVertex.get(0).get(1).vertexNum(), 1000);
    t.checkExpect(mSmall.mazeVertex.get(1).get(1).vertexNum(), 1001);
    t.checkExpect(mSmall.mazeVertex.get(2).get(2).vertexNum(), 2002);
  }

  void testEquals(Tester t) {
    t.checkExpect(mSmall.mazeVertex.get(0).get(0).equals(mSmall.mazeVertex.get(0).get(0)), true);
    t.checkExpect(mSmall.mazeVertex.get(1).get(0).equals(mSmall.mazeVertex.get(0).get(0)), false);
    t.checkExpect(mSmall.mazeVertex.get(0).get(1).equals(mSmall.mazeVertex.get(0).get(0)), false);
    t.checkExpect(mSmall.mazeVertex.get(1).get(1).equals(mSmall.mazeVertex.get(0).get(0)), false);
  }

  void testHashCode(Tester t) {
    t.checkExpect(mSmall.mazeVertex.get(0).get(0).hashCode(), 0);
    t.checkExpect(mSmall.mazeVertex.get(1).get(0).hashCode(), 1);
    t.checkExpect(mSmall.mazeVertex.get(0).get(1).hashCode(), 1000);
    t.checkExpect(mSmall.mazeVertex.get(1).get(1).hashCode(), 1001);
    t.checkExpect(mSmall.mazeVertex.get(2).get(2).hashCode(), 2002);
  }

  void testOnKeyEvent(Tester t) {
    mSmall.bfsPressed = false;
    mSmall.dfsPressed = false;
    mSmall.userTurn = false;

    mSmall.onKeyEvent("m");
    t.checkExpect(mSmall.userTurn, true);
    t.checkExpect(mSmall.bfsPressed, false);
    t.checkExpect(mSmall.dfsPressed, false);
    mSmall.onKeyEvent("b");
    t.checkExpect(mSmall.userTurn, false);
    t.checkExpect(mSmall.bfsPressed, true);
    t.checkExpect(mSmall.dfsPressed, false);
    mSmall.onKeyEvent("t");
    t.checkExpect(mSmall.toggle, true);
    mSmall.onKeyReleased("t");
    t.checkExpect(mSmall.toggle, false);
    mSmall.onKeyEvent("d");
    t.checkExpect(mSmall.userTurn, false);
    t.checkExpect(mSmall.bfsPressed, false);
    t.checkExpect(mSmall.dfsPressed, true);
    mSmall.onKeyEvent("m");
    mSmall.onKeyEvent("up");
    t.checkExpect(mSmall.userPressedUp, true);
    t.checkExpect(mSmall.userPressedDown, false);
    t.checkExpect(mSmall.userPressedLeft, false);
    t.checkExpect(mSmall.userPressedRight, false);
    mSmall.onKeyEvent("down");
    t.checkExpect(mSmall.userPressedUp, false);
    t.checkExpect(mSmall.userPressedDown, true);
    t.checkExpect(mSmall.userPressedLeft, false);
    t.checkExpect(mSmall.userPressedRight, false);
    mSmall.onKeyEvent("left");
    t.checkExpect(mSmall.userPressedUp, false);
    t.checkExpect(mSmall.userPressedDown, false);
    t.checkExpect(mSmall.userPressedLeft, true);
    t.checkExpect(mSmall.userPressedRight, false);
    mSmall.onKeyEvent("right");
    t.checkExpect(mSmall.userPressedUp, false);
    t.checkExpect(mSmall.userPressedDown, false);
    t.checkExpect(mSmall.userPressedLeft, false);
    t.checkExpect(mSmall.userPressedRight, true);
    mSmall.onKeyEvent("r");
    t.checkExpect(mSmall.bfsPressed, false);
    t.checkExpect(mSmall.dfsPressed, false);
    t.checkExpect(mSmall.userTurn, false);
    t.checkExpect(mSmall.solvedMaze, false);
  }

  void testReset(Tester t) {
    mSmall.bfsPressed = true;
    mSmall.reset();
    t.checkExpect(mSmall.bfsPressed, false);
    mSmall.dfsPressed = true;
    mSmall.reset();
    t.checkExpect(mSmall.dfsPressed, false);
    mSmall.userTurn = true;
    mSmall.reset();
    t.checkExpect(mSmall.userTurn, false);
    mSmall.solvedMaze = true;
    mSmall.reset();
    t.checkExpect(mSmall.solvedMaze, false);
    mSmall.height = 10;
    mSmall.width = 10;
    mSmall.reset();
    t.checkExpect(mSmall.height, 10);
    t.checkExpect(mSmall.width, 10);
    mSmall.userPressedUp = true;
    mSmall.reset();
    t.checkExpect(mSmall.userPressedUp, false);
    mSmall.userPressedDown = true;
    mSmall.reset();
    t.checkExpect(mSmall.userPressedDown, false);
    mSmall.userPressedLeft = true;
    mSmall.reset();
    t.checkExpect(mSmall.userPressedLeft, false);
    mSmall.userPressedRight = true;
    mSmall.reset();
    t.checkExpect(mSmall.userPressedRight, false);


  }

  void testGetVertex(Tester t) {
    ArrayList<ArrayList<Vertex>> vertices = new ArrayList<>();
    for (int x = 0; x < mSmall.width; x++) {
      ArrayList<Vertex> v = new ArrayList<>();
      for (int y = 0; y < mSmall.height; y++) {
        v.add(new Vertex(x, y));
      }
      vertices.add(v);

      t.checkExpect(mSmall.getVertex(), mSmall.vertices);
    }

  }

  void testReconstructs(Tester t) {
    HashMap<String, Edge> hashmap = new HashMap<String, Edge>();
    mSmall.rightMovesCount = 0;
    mSmall.endCurrent = mSmall.mazeVertex.get(0).get(0);
    mSmall.reconstruct(hashmap);
    t.checkExpect(mSmall.endCurrent, mSmall.mazeVertex.get(0).get(0));
    mSmall.rightMovesBFS = 0;
    mSmall.endCurrentBFS = mSmall.mazeVertex.get(0).get(0);
    mSmall.reconstructBFS(hashmap);
    t.checkExpect(mSmall.endCurrentBFS, mSmall.mazeVertex.get(0).get(0));
    mSmall.userStart = mSmall.mazeVertex.get(0).get(0);
    t.checkExpect(mSmall.endCurrentBFS, mSmall.mazeVertex.get(0).get(0));

  }

  void testOnTick(Tester t) {
    mSmall.bfsPressed = true;
    mSmall.onTick();
    t.checkExpect(mSmall.bfsPressed, true);
    mSmall.dfsPressed = true;
    mSmall.onTick();
    t.checkExpect(mSmall.dfsPressed, true);
    mSmall.userTurn = true;
    mSmall.onTick();
    t.checkExpect(mSmall.userTurn, true);
    mSmall.height = 10;
    mSmall.width = 10;
    mSmall.onTick();
    t.checkExpect(mSmall.height, 10);
    t.checkExpect(mSmall.width, 10);
    mSmall.onKeyEvent("down");
    mSmall.onTick();
    t.checkExpect(mSmall.userPressedDown, true);
    mSmall.onKeyEvent("up");
    mSmall.onTick();
    t.checkExpect(mSmall.userPressedUp, true);
    mSmall.onKeyEvent("left");
    mSmall.onTick();
    t.checkExpect(mSmall.userPressedLeft, true);
    mSmall.onKeyEvent("right");
    mSmall.onTick();
    t.checkExpect(mSmall.userPressedRight, true);
  }

  void testManualMode(Tester t) {
    mSmall.userTurn = true;
    mSmall.userStart = mSmall.mazeVertex.get(mSmall.mazeVertex.size() - 5)
            .get(mSmall.mazeVertex.get(0).size() - 5);
    mSmall.userPressedUp = true;
    mSmall.manualMode();
    mSmall.userPressedUp = true;
    mSmall.manualMode();
    t.checkExpect(mSmall.solvedUser, false);
    mSmall.userPressedLeft = true;
    mSmall.manualMode();
    t.checkExpect(mSmall.solvedUser, false);
    mSmall.userPressedRight = true;
    mSmall.manualMode();
    t.checkExpect(mSmall.solvedUser, false);
    mSmall.userPressedDown = true;
    mSmall.manualMode();
    t.checkExpect(mSmall.solvedUser, false);

  }

  void testDfs(Tester t) {
    Maze m = new Maze(5, 5);
    Stack<Vertex> stack = new Stack<Vertex>();
    stack.add(m.mazeVertex.get(0).get(0));
    m.onTick();
    m.solvedMaze = false;
    m.onKeyEvent("d");
    m.onTick();
    m.dfs(stack);
    m.onTick();
    stack.add(m.mazeVertex.get(0).get(1));
    m.dfs(stack);
    m.onTick();
    stack.add(m.mazeVertex.get(0).get(2));
    m.dfs(stack);
    m.onTick();
    stack.add(m.mazeVertex.get(4).get(4));

    t.checkExpect(m.visited.contains(m.mazeVertex.get(0).get(0)), true);
    t.checkExpect(m.visited.contains(m.mazeVertex.get(0).get(1)), true);
    t.checkExpect(m.visited.contains(m.mazeVertex.get(0).get(2)), true);
    t.checkExpect(m.visited.contains(m.mazeVertex.get(4).get(4)), false);


  }


  void testBfs(Tester t) {
    Maze m = new Maze(5, 5);
    Queue<Vertex> queue = new LinkedList<Vertex>();
    queue.add(m.mazeVertex.get(0).get(0));
    m.onTick();
    m.onKeyEvent("b");
    m.onTick();
    m.bfs(queue);
    m.onTick();
    queue.add(m.mazeVertex.get(0).get(1));
    m.bfs(queue);
    m.onTick();
    queue.add(m.mazeVertex.get(0).get(2));
    m.bfs(queue);
    m.onTick();
    queue.add(m.mazeVertex.get(4).get(4));

    t.checkExpect(m.visitedBFS.contains(m.mazeVertex.get(0).get(0)), true);
    t.checkExpect(m.visitedBFS.contains(m.mazeVertex.get(0).get(1)), true);
    t.checkExpect(m.visitedBFS.contains(m.mazeVertex.get(0).get(2)), true);
    t.checkExpect(m.visited.contains(m.mazeVertex.get(4).get(4)), false);


  }

  void testReconstructBFS(Tester t) {
    Maze m = new Maze(4, 4);
    HashMap<String, Edge> hashmap = new HashMap<String, Edge>();

    m.endCurrentBFS = m.mazeVertex.get(0).get(1);
    hashmap.put("0,1", new Edge(m.mazeVertex.get(0).get(0), m.mazeVertex.get(0).get(1), 0));
    hashmap.put("0,0", new Edge(m.mazeVertex.get(0).get(0), m.mazeVertex.get(0).get(0), 0));


    m.endCurrentBFS = m.mazeVertex.get(0).get(0);
    m.reconstructBFS(hashmap);

    t.checkExpect(m.endCurrentBFS, m.mazeVertex.get(0).get(0));
    t.checkExpect(m.endCurrentBFS.equals(m.mazeVertex.get(0).get(1)), false);
    t.checkExpect(m.endCurrentBFS.equals(m.mazeVertex.get(0).get(0)), true);


  }

  void testReconstructDFS(Tester t) {
    Maze m = new Maze(4, 4);
    HashMap<String, Edge> hashmap = new HashMap<String, Edge>();

    m.endCurrent = m.mazeVertex.get(0).get(1);
    hashmap.put("0,1", new Edge(m.mazeVertex.get(0).get(0), m.mazeVertex.get(0).get(1), 0));
    hashmap.put("0,0", new Edge(m.mazeVertex.get(0).get(0), m.mazeVertex.get(0).get(0), 0));


    m.endCurrent = m.mazeVertex.get(0).get(0);
    m.reconstruct(hashmap);

    t.checkExpect(m.endCurrent, m.mazeVertex.get(0).get(0));
    t.checkExpect(m.endCurrent.equals(m.mazeVertex.get(0).get(1)), false);
    t.checkExpect(m.endCurrent.equals(m.mazeVertex.get(0).get(0)), true);


  }

*/
}