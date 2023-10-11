import sys

sys.setrecursionlimit(10**9)


def main():
    x1, y1, x2, y2 = map(int, input().split())

    if abs(x1 - x2) <= 4 and abs(y1 - y2) <= 4:
        if abs(x1 - x2) == 1 and abs(y1 - y2) in [1, 3]:
            print("Yes")
        elif abs(x1 - x2) == 0 and abs(y1 - y2) in [2, 4]:
            print("Yes")
        elif abs(x1 - x2) == 2 and abs(y1 - y2) in [0, 4]:
            print("Yes")
        elif abs(x1 - x2) == 3 and abs(y1 - y2) in [1, 3]:
            print("Yes")
        elif abs(x1 - x2) == 4 and abs(y1 - y2) in [0, 2]:
            print("Yes")
        else:
            print("No")
    else:
        print("No")


if __name__ == "__main__":
    main()
