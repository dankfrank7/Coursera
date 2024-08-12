package Week5;

import java.util.*;

public class OrthogonalLineSegement {
    
    static class Segment {
        int x1, y1, x2, y2; 
        boolean isVertical;

        Segment(int x1, int y1, int x2, int y2) {
            if (x1 == x2) {
                this.x1 = x1;
                this.x2 = x2;
                this.y1 = Math.min(y1, y2);
                this.y2 = Math.max(y1, y2);
                this.isVertical = true;
            } else {
                this.x1 = Math.min(x1, x2);
                this.x2 = Math.max(x1, x2);
                this.y1 = y1;
                this.y2 = y2;
                this.isVertical = false;
            }
        }
    }

    static class Event implements Comparable<Event> {
        int x;
        Segment segment;
        boolean isStart;

        Event(int x, Segment segment, boolean isStart) {
            this.x = x;
            this.segment = segment;
            this.isStart = isStart;            
        }

        // we want to compare segments by x-coordiante
        @Override 
        public int compareTo(Event other) {
            return Integer.compare(this.x, other.x);
        }
    }

    private RangeSearchBST<Integer, Segment> bst = new RangeSearchBST<>();

    public List<String> findIntersections(List<Segment> segments) {
        List<Event> events = new ArrayList<>();
        List<String> intersections = new ArrayList<>();

        //create events for all segment endpoints
        for (Segment segment: segments) {
            if (segment.isVertical) {
                events.add(new Event(segment.x1, segment, true));
            } else {
                events.add(new Event(segment.x1, segment, true)); // left endpoint
                events.add(new Event(segment.x2, segment, false)); // right endpoint
            }
        }
        
        // sort events by x-coordinate
        Collections.sort(events);

        // process events 
        for (Event event : events) {
            Segment seg = event.segment;
            if (seg.isVertical) {
                if (event.isStart) {
                    // range search between y1 and y2 of the vertical segment
                    Iterable<Integer> ys = bst.rangeSearch(seg.y1, seg.y2);
                    for (Integer y : ys) {
                        intersections.add("(" + seg.x1 + ", " + y + ")");
                    }
                }
            } else {
                if (event.isStart) {
                    bst.put(seg.y1, seg);
                } else {
                    bst.delete(seg.y1);
                }
            }
        }   
        return intersections;
    }

    public static void main(String[] args) {
        List<Segment> segments = Arrays.asList(
            new Segment(1, 2, 1, 5),  // Vertical
            new Segment(0, 3, 3, 3),  // Horizontal
            new Segment(2, 1, 2, 4),  // Vertical
            new Segment(0, 4, 3, 4)   // Horizontal
        );

        OrthogonalLineSegement solver = new OrthogonalLineSegement();
        List<String> intersections = solver.findIntersections(segments);
        System.out.println("Intersections: ");
        for (String intersection : intersections) {
            System.out.println(intersection);
        }
    }

}
