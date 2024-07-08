T = int(input())
for _ in range(T):
    N, A, B = map(int, input().split())

    if A > N:
        print('No')
        continue
    if A == 0:
        h = (N + 1) // 2
    elif N % 2 == 0:
        if A <= N // 2:
            h = N // 2
        else:
            h = N - A
    else:
        if A <= N // 2:
            h = N // 2 + 1
        else:
            h = N - A
    w = N - A


    if A > N or B > h * w:
        print('No')
        continue
    print('Yes')