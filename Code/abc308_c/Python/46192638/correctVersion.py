import sys

input = sys.stdin.readline
# sys.setrecursionlimit(10**6)

def main():
    N = int(input())
    l = []
    for i in range(N):
        a, b = map(int, input().split())
        l.append((-a * 10 ** 100 // (a + b), i + 1))
    l.sort()
    for i in range(N):
        print(l[i][1], end=' ')
    print()

if __name__ == '__main__':
    main()
