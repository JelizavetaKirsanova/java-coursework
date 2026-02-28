package junit.sales;

public class TopSalesFinder {

    private static class DynamicArray {
        private SalesRecord[] data;
        private int size;

        public DynamicArray() {
            data = new SalesRecord[4];
            size = 0;
        }

        public void add(SalesRecord record) {
            if (size == data.length) {
                SalesRecord[] newData = new SalesRecord[data.length * 2];
                for (int i = 0; i < data.length; i++) {
                    newData[i] = data[i];
                }
                data = newData;
            }
            data[size++] = record;
        }

        public int size() {
            return size;
        }

        public SalesRecord get(int index) {
            return data[index];
        }

        public void removeByProductId(String productId) {
            int writeIndex = 0;
            for (int readIndex = 0; readIndex < size; readIndex++) {
                if (!data[readIndex].productId().equals(productId)) {
                    data[writeIndex++] = data[readIndex];
                }
            }
            size = writeIndex;
        }

        public void removeByRecordId(String recordId) {
            int writeIndex = 0;
            for (int readIndex = 0; readIndex < size; readIndex++) {
                if (!data[readIndex].recordId().toString().equals(recordId)) {
                    data[writeIndex++] = data[readIndex];
                }
            }
            size = writeIndex;
        }

        public SalesRecord[] getSlice(int start, int length) {
            int actualLength = Math.min(length, size - start);
            if (actualLength <= 0) return new SalesRecord[0];

            SalesRecord[] slice = new SalesRecord[actualLength];
            for (int i = 0; i < actualLength; i++) {
                slice[i] = data[start + i];
            }
            return slice;
        }

        public SalesRecord[] getAll() {
            return getSlice(0, size);
        }
    }

    private final DynamicArray records = new DynamicArray();

    public void registerSale(SalesRecord record) {
        records.add(record);
    }

    public SalesRecordResult[] findItemsSoldOver(int amount) {
        String[] productIds = new String[records.size()];
        int[] revenues = new int[records.size()];
        int uniqueCount = 0;

        for (int i = 0; i < records.size(); i++) {
            SalesRecord record = records.get(i);
            int revenue = record.productPrice() * record.itemsSold();

            int index = -1;
            for (int j = 0; j < uniqueCount; j++) {
                if (productIds[j].equals(record.productId())) {
                    index = j;
                    break;
                }
            }

            if (index >= 0) {
                revenues[index] += revenue;
            } else {
                productIds[uniqueCount] = record.productId();
                revenues[uniqueCount] = revenue;
                uniqueCount++;
            }
        }

        int count = 0;
        for (int i = 0; i < uniqueCount; i++) {
            if (revenues[i] > amount) count++;
        }

        SalesRecordResult[] results = new SalesRecordResult[count];
        int idx = 0;
        for (int i = 0; i < uniqueCount; i++) {
            if (revenues[i] > amount) {
                results[idx++] = new SalesRecordResult(productIds[i], revenues[i]);
            }
        }

        return results;
    }

    public void removeSalesRecordsFor(String id) {
        records.removeByProductId(id);
    }

    public SalesRecord[] getAllRecordsPaged(int pageNumber, int pageSize) {
        int start = pageNumber * pageSize;
        return records.getSlice(start, pageSize);
    }

    public int getRecordCount() {
        return records.size();
    }

    public void removeRecord(String id) {
        records.removeByRecordId(id);
    }

}


