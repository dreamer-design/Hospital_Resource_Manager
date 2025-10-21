package dsa.data;

/**
 *
 */
    public class Request {
        private Integer priority; // high record = highet priority
        private Patient record;
        
        public Request() {
        }

        public Request(int priority, Patient record) {
            this.priority = priority;
            this.record = record;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public Patient getValue() {
            return record;
        }

        public void setValue(Patient record) {
            this.record = record;
        }
        
        @Override
        public String toString() {
            return record.toString();
        }
    }
