package face_book.hard;

/**
 * 158. Read N Characters Given Read4 II - Call multiple times
 * Given a file and assume that you can only read the file using a given method read4,
 * implement a method read to read n characters. Your method read may be called multiple times.
 *
 * Method read4:
 *
 * The API read4 reads four consecutive characters from file, then writes those characters into the buffer array buf4.
 *
 * The return value is the number of actual characters read.
 *
 * Note that read4() has its own file pointer, much like FILE *fp in C.
 *
 * Definition of read4:
 *
 *     Parameter:  char[] buf4
 *     Returns:    int
 *
 * buf4[] is a destination, not a source. The results from read4 will be copied to buf4[].
 *
 */
public class Reader extends Reader4 {

    public static void main(String [] args) {
        String test = "abc";
        char [] buffer = new char[10];
        Reader reader = new Reader();
        int i = reader.read(buffer, 1);
        i = reader.read(buffer, 2);
        i = reader.read(buffer, 1);
        System.out.println(i);
    }

    char [] buffer = {' ', ' ', ' ', ' '};
    int bufferIndex = 4;
    int retVal = -1;

    public int read(char[] buf, int n) {
        int numRead = 0;
        while (numRead < n && retVal != 0) {
            if (bufferIndex == retVal || retVal == -1) {
                retVal = read4(buffer);
                bufferIndex = 0;
            }
            for ( ; bufferIndex < retVal && numRead < n; numRead++, bufferIndex++) {
                buf[numRead] = buffer[bufferIndex];
            }
        }
        return numRead;
    }
}
