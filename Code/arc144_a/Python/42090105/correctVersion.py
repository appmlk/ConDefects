import sys

read = sys.stdin.read
readline = sys.stdin.readline
readlines = sys.stdin.readlines


def main():
    N = int(readline())

    print(2 * N)

    ans = '4' * (N // 4)

    if N % 4:
        ans = str(N % 4) + ans
    
    print(ans)


if __name__ == '__main__':
    main()
