import java.util.HashMap;
import java.util.Map;

class LRUCache {

    private Map<Integer, Entry> cache;

    private Entry head;

    private Entry tail;

    private int maxCapacity;

    public LRUCache(int capacity) {
        this.maxCapacity = capacity;
        this.cache = new HashMap<>();
        this.head = null;
        this.tail = null;
    }

    public int get(int key) {
        Entry entry = this.cache.get(key);
        if (entry != null) {
            refreshEntry(entry);
            return entry.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        Entry oldEntry = this.cache.get(key);
        if (oldEntry != null) {
            oldEntry.value = value;
            refreshEntry(oldEntry);
        } else {
            refreshCacheIfNeedBeforePut();
            Entry newEntry = new Entry(key, value);
            this.cache.put(key, newEntry);
            linkEntry(newEntry);
        }
    }

    private void refreshCacheIfNeedBeforePut() {
        int cacheSize = this.cache.size();
        if (cacheSize >= maxCapacity) {
            if (this.head != null) {
                this.cache.remove(this.head.key);
                removeEntry(this.head);
            }
        }
    }

    private void refreshEntry(Entry entry) {
        removeEntry(entry);
        linkEntry(entry);
    }

    private void removeEntry(Entry entry) {
        Entry prev = entry.prev;
        Entry next = entry.next;
        if (prev != null) {
            prev.next = next;
        }
        if (next != null) {
            next.prev = prev;
        }
        if (this.head == entry) {
            this.head = next;
        }
        if (this.tail == entry) {
            this.tail = prev;
        }
    }

    private void linkEntry(Entry entry) {
        if (this.head == null) {
            this.head = entry;
        } else {
            entry.prev = this.tail;
            this.tail.next = entry;
        }
        this.tail = entry;
        this.tail.next = null;
        this.head.prev = null;
    }

    static class Entry {
        public int key;
        public int value;
        public Entry next;
        public Entry prev;

        public Entry(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */