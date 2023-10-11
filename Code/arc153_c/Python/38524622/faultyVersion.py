import sys
import itertools


def input():
    return sys.stdin.readline().rstrip()


def main():
    n = int(input())
    a = list(map(int, input().split()))
    b = list(range(n))

    pre = list(itertools.accumulate(a, initial=0))

    def success():
        print("Yes")
        print(*b)

    total = sum(i*a[i] for i in range(n))
    if total == 0:
        success()
        return
    # make total > 0
    if total < 0:
        total = -total
        a = [-x for x in a]
        pre = [-x for x in pre]

    for i in range(1, n+1):
        if pre[i] == 1:
            for j in range(i):
                b[j] -= total
            success()
            return
    for i in range(n-1, 0, -1):
        if pre[-1] - pre[i] == -1:
            for j in range(i, n):
                b[j] += total
            success()
            return
    print("No")


if __name__ == "__main__":
    main()
