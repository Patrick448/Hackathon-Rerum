.source testex.j


.class public testex
.super java/lang/Object

    .method public static _func()Ljava/util/ArrayList;
        .limit stack 11
        .limit locals 6
         iconst_0
         istore_1
        getstatic java/lang/System/out Ljava/io/PrintStream;
        ldc 2
        istore_0
        iload_0
        invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
        invokevirtual java/io/PrintStream/print(Ljava/lang/Object;)V
        new java/util/ArrayList
        dup
        invokespecial java/util/ArrayList/<init>()V
        astore_2
        aload_2
        ldc 1.5
        fstore_0
        fload_0
        invokestatic java/lang/Float/valueOf(F)Ljava/lang/Float;
        invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
        pop
        aload_2
        ldc 8
        istore_0
        iload_0
        invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
        invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
        pop
        aload_2
        areturn
        return
    .end method

    .method public static _func2()V
        .limit stack 11
        .limit locals 6
         iconst_0
         istore_1
        getstatic java/lang/System/out Ljava/io/PrintStream;
        ldc 333
        istore_0
        iload_0
        invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
        invokevirtual java/io/PrintStream/print(Ljava/lang/Object;)V
        return
    .end method

    .method public static _main()V
        .limit stack 11
        .limit locals 8
         iconst_0
         istore_1
        invokestatic testex/_func()V
        aload_0 
        ldc 0
        invokevirtual java/util/ArrayList/get(I)Ljava/lang/Object;
        fstore_num
        aload_0 
        ldc 1
        invokevirtual java/util/ArrayList/get(I)Ljava/lang/Object;
        istore_num
        getstatic java/lang/System/out Ljava/io/PrintStream;
        fload 3
        invokestatic java/lang/Float/valueOf(F)Ljava/lang/Float;
        invokevirtual java/io/PrintStream/print(Ljava/lang/Object;)V
        return
    .end method

    .method public static main([Ljava/lang/String;)V
        .limit stack 1
        invokestatic testex/_main()V
        return
    .end method