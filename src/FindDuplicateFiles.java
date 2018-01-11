import java.nio.file.Path;
import java.nio.file.Files;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

public class FindDuplicateFiles
{
Vector<FilesPair> duplicateFiles;

public void find(Path dir) throws IOException
{
        duplicateFiles = new Vector<FilesPair>();
        HashMap<Long, Vector<FileComparator> > indexFiles = new HashMap<Long, Vector<FileComparator> >();

        IndexVisitor indexVisitor = new IndexVisitor();
        indexVisitor.indexFiles = indexFiles;
        Files.walkFileTree(dir, indexVisitor);

        for (Vector<FileComparator> fileComparators : indexFiles.values()) {
                for (int i = 0; i < fileComparators.size(); ++i) {
                        for (int j = i + 1; j < fileComparators.size(); ++j) {
                                File f1 = fileComparators.elementAt(i)._file;
                                File f2 = fileComparators.elementAt(j)._file;
                                if (!f1.toPath().equals(f2.toPath())) {
                                        if (fileComparators.elementAt(i).equals(fileComparators.elementAt(j))) {
                                                duplicateFiles.addElement(new FilesPair(f1, f2));
                                        }
                                }
                        }
                }
        }
}

public void find(Path dir1, Path dir2) throws IOException
{
        duplicateFiles = new Vector<FilesPair>();
        HashMap<Long, Vector<FileComparator> > indexFiles = new HashMap<Long, Vector<FileComparator> >();

        IndexVisitor indexVisitor = new IndexVisitor();
        indexVisitor.indexFiles = indexFiles;
        Files.walkFileTree(dir1, indexVisitor);

        CompareFileVisitor compareFileVisitor = new CompareFileVisitor();
        compareFileVisitor.duplicateFiles = duplicateFiles;
        compareFileVisitor.indexFiles = indexFiles;
        Files.walkFileTree(dir2, compareFileVisitor);
}
}
