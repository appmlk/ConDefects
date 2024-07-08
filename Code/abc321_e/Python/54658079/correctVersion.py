def solve():
    n,x,k = list(map(int, input().split(' ')))
    # print(n,x,k)
    n += 1
    k = min(1000,k)
    ans = 0
    depth = k
    prev = -1
    while x and depth >= 0:
        # print(f'{x=} {depth=} {prev=}')
        if prev == -1:
            L = x*(1<<depth)
            R = L + (1<<depth)
        else:
            if depth == 0:
                L = x
                R = x+1
            else:
                another_way = prev^1
                # print(f'{another_way=}')
                L = another_way*(1<<(depth-1))
                R = L + (1<<(depth-1))
        # print(L,R)
        intersection = max(0,min(R,n)-max(0,L))
        # print(f'{intersection=}')
        ans += intersection
        prev = x
        x //= 2
        depth -= 1
    # print(f'{ans=}')
    return ans

t = int(input())

for _ in range(t):
    print(solve())