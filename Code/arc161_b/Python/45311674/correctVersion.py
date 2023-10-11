T = int(input())
for _ in range(T):
    N = int(input())
    if N < 7:
        print(-1)
        continue
    cnt = bin(N).count('1')
    if cnt == 1:
        print((N>>1) + (N>>2) + (N>>3))
    elif cnt == 2:
        if N & 1 or N & 2:
            print((N>>1) + (N>>2) + (N>>3) - (N&2)//2)
        else:
            s = 0
            while N & (1<<s) == 0:
                s += 1
            print(N - (1<<s) + (1<<(s-1)) + (1<<(s-2)))
    else:
        tmp = 0
        for i in range(60):
            if cnt - tmp == 3:
                break
            if N & (1<<i):
                N ^= 1<<i
                tmp += 1
        print(N)