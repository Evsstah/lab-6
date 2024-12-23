@ToString
class A3 extends Entity3 {
    private final String s = "hello";
    @ToString(value = ToString.Value.NO)
    int x = 42;
}
