from itertools import combinations


def getIntMap():
    return map(int, input().split())


def getIntList():
    return list(map(int, input().split()))


def main():
    N, W = getIntMap()
    A = getIntList()

    res = set()
    for i in range(1, 4):
        for elem in combinations(A, i):
            if sum(elem) <= W:
                res.add(sum(elem))

    print(len(res))

if __name__ == "__main__":
    main()
