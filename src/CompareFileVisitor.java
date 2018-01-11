import java.nio.file.SimpleFileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.FileVisitResult;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

public class CompareFileVisitor extends SimpleFileVisitor<Path>
{
HashMap<Long, Vector<FileComparator> > indexFiles;
Vector<FilesPair> duplicateFiles;

public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
throws IOException
{
        File f = file.toFile();
        if (f.canRead()) {
                Vector<FileComparator> fcs = indexFiles.get(f.length());
                if (fcs != null) {
                        for (FileComparator fc : fcs) {
                                if (fc.equals(new FileComparator(f))) {
                                        duplicateFiles.addElement(new FilesPair(f, fc._file));
                                }
                        }
                }
        }
        return FileVisitResult.CONTINUE;
}
}
