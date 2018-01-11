import java.io.File;

public class FilesPair
{
File first;
File second;

FilesPair()
{
        first = null;
        second = null;
}

FilesPair(File first, File second)
{
        this.first = first;
        this.second = second;
}

boolean equals(FilesPair fp)
{
        String f1_1 = first.getAbsolutePath();
        String f1_2 = second.getAbsolutePath();
        String f2_1 = fp.first.getAbsolutePath();
        String f2_2 = fp.second.getAbsolutePath();
        return (f1_1.equals(f2_1) && f1_2.equals(f2_2)) || (f1_1.equals(f2_2) && f1_2.equals(f2_1));
}
}
