```java

► Primeiro Algoritimo

[Recursão por Soma]

    int retVal = 0;
    int retVal = a + prod(a, b-1);

    [PROCESO]

       Inicio
    [ prod(3,5) ] ==> prod(3,4) ==> prod(3,3) ==> prod(3,2) ==> prod(3,1) 
       [3 + 12]   <==  [3+ 9]    <==  [3+6]  <==  [ 3 + 3 ]   <==  [ 3 ]
        Fim
    
    [Pilha]

    main => main | prod => main | prod | prod => ... => main => fim
