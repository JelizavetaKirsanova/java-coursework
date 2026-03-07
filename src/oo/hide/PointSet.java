package oo.hide;

import java.util.Objects;

public class PointSet {

    private Point[] points;
    private int size;

    public PointSet(int capacity) {
        points = new Point[capacity];
        size = 0;


    }

    public PointSet() {
        this(10);
    }

    private void ensureCapacity() {
        if (size >= points.length) {
            int newLength = points.length == 0 ? 1 : points.length * 2;
            Point[] newArray = new Point[newLength];
            for (int i = 0; i < size; i++) {
                newArray[i] = points[i];
            }


            points = newArray;
        }
    }

    public void add(Point point) {
        if (contains(point)) {
            return;

        }

        ensureCapacity();
        points[size] = point;
        size++;
    }

    public int size() {
        return size;

    }

    public boolean contains(Point point) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(points[i], point)) {
                return true;
            }
        }
        return false;
    }

    public PointSet subtract(PointSet other) {
        PointSet result = new PointSet(size);

        for (int i = 0; i < size; i++) {
            if (!other.contains(points[i])) {
                result.add(points[i]);


            }
        }

        return result;
    }



    public PointSet intersect(PointSet other) {
        PointSet result = new PointSet();

        for (int i = 0; i < size; i++) {
            if (other.contains(points[i])) {
                result.add(points[i]);
            }
        }

        return result;
    }

    public void remove(Point point) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(points[i], point)) {
                for (int j = i; j < size - 1; j++) {
                    points[j] = points[j + 1];
                }



                size--;
                return;
            }
        }
    }

    @Override
    public String toString() {
        String result = "";

        for (int i = 0; i < size; i++) {
            if (points[i] == null) {
                result += "null";
            } else {
                result += "(" + points[i].x + ", " + points[i].y + ")";
            }

            if (i < size - 1) {
                result += ", ";
            }
        }

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {return true;}
        if (!(obj instanceof PointSet)) {return false;}

        PointSet other = (PointSet) obj;

        if (size != other.size) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (!other.contains(points[i])) {
                return false;
            }
        }

        return true;
    }





    @Override
    public int hashCode() {
        return 0; // no need to change this
    }
}