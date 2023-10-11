N=int(input())
bad=set()
for _ in range(N+1):
    for i in range(1,2*N+2):
        if i not in bad:
            bad.add(i)
            print(i)
            bad.add(int(input()))
            break

        