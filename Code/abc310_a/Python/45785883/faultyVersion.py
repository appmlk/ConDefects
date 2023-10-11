def getIntMap():
    return map(int, input().split())


def getIntList():
    return list(map(int, input().split()))


def main():
    n, p, q = getIntMap()
    a = getIntList()
    b = min([p - q + a[i] for i in range(n)])

    print(b if b < p else p)


if __name__ == "__main__":
    main()
