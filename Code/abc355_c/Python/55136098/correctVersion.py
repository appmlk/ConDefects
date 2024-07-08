N, T = map(int, input().split(' '))
A = tuple(map(int, input().split(' ')))

def main():
    rows = [0 for i in range(N)]
    cols = [0 for i in range(N)]

    backwards = 0
    forwards = 0

    for i, a in enumerate(A):
        cols[(a-1) % N] += 1
        if cols[(a-1) % N] == N:
            # print(cols)
            return i + 1
        rows[(a-1) // N] += 1
        if rows[(a-1) // N] == N:
            # print(rows)
            return i + 1

        if (a - 1) % (N + 1) == 0:
            backwards += 1
            if backwards == N:
                # print("backwards")
                return i + 1
            
        if (a - N) % (N - 1) == 0 and a != N ** 2 and a != 1:
            forwards += 1
            if forwards == N:
                # print("forwards")
                return i + 1

    return -1

print(main())