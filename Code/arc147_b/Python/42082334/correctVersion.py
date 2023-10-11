import sys

def main():
    input = sys.stdin.readline
    N = int(input())
    P = [int(p) for p in input().strip().split()]
    ans = []

    def calc1(i):
        if i <= 2:
            return True
        if P[i-3] % 2 != (i-2) % 2:
            return True
        ans.append(('B', i-2))
        P[i-3], P[i-1] = P[i-1], P[i-3]
        calc1(i-2)
        return True

    def calc2():
        for i in range(1, N, 2):
            if P[i-1] % 2 != i % 2:
                ans.append(('A', i))
                P[i-1], P[i] = P[i], P[i-1]
            else:
                return True

    def calc3(n):
        i = N
        while P[i-1] != n:
            i -= 1
        while i != n:
            ans.append(('B', i-2))
            P[i-3], P[i-1] = P[i-1], P[i-3]
            i -= 2

        return True

    for i in range(1, N+1):
        if P[i-1] % 2 != i % 2:
            calc1(i)

    calc2()

    for n in range(1, N+1):
        calc3(n)

    print(len(ans))
    for a in ans:
        print(*a)

if __name__ == '__main__':
    main()
