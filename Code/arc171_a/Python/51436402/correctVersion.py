T = int(input())
for _ in range(T):
    N, A, B = map(int,input().split())
    if N < A:
        print("No")
        continue
    if N//2 >= A:
        tate = (N - 2*A + 1)//2 + A
        yoko = N - A
        if tate*yoko >= B:
            print("Yes")
        else:
            print("No")
    else:
        yoko = N - A
        A -= N // 2
        tate = N - N//2 - A
        if tate*yoko >= B:
            print("Yes")
        else:
            print("No")