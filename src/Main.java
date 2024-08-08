import java.nio.file.DirectoryIteratorException;
import java.time.Clock;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.*;

public class Main {



    public static void main(String[] args) throws Exception {

      var cars = new ArrayList<Car>();
      cars.add(new Car("Mustang", 1967));
        cars.add(new Car("Thunderbird", 1967));
        cars.add(new Car("Escort", 1975));
        var map = cars.stream().collect(Collectors.groupingByConcurrent(Car::year));
        System.out.println(map);
    }
}

record Car(String model, int year){
    @Override public String toString(){return model;}
}






