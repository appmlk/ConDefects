def getIntMap():
    return map(int, input().split())


def main():
    a, b = getIntMap()

    print(a*b)


main()