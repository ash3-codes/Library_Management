public class Audiobook extends LibraryItem implements Playable {
    private final double duration; // in minutes

    public Audiobook(int itemId, String title, String author, double duration) throws IllegalArgumentException {
        super(itemId, title, author);
        if (duration <= 0) {
            throw new IllegalArgumentException("Duration must be a positive number");
        }
        this.duration = duration;
    }

    @Override
    public String getItemType() {
        return "Audiobook";
    }

    @Override
    public void play() {
        System.out.println("Playing audiobook: " + getTitle());
    }

    @Override
    public void pause() {
        System.out.println("Pausing audiobook: " + getTitle());
    }

    @Override
    public void stop() {
        System.out.println("Stopping audiobook: " + getTitle());
    }

    @Override
    public double getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return super.toString() + ", Duration=" + duration + " minutes";
    }
}