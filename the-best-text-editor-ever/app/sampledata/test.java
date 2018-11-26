class foo extends YoMomma implements Scolder {

    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            String.format("%dth argument is '%s'!", i, args[i]);
        }
    }

    @Override
    public static String scold(String child) {
        return String.format("Tsk, tsk, %s! You know better than that!", child);
    }
    
}