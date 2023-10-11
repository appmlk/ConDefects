if __name__ == '__main__':
    n = int(input())
    a = list(map(int, input().split()))
    x, y = a[n - 2], a[n - 1]
    if x + 1 < y or (y - x) % 2 == 0:
        print("Alice")
    else:
        print("Bob")
