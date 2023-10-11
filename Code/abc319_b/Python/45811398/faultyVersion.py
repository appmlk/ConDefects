
import sympy as sp


def solver():
    N = int(input())

    divisors = sp.divisors(N)
    # divisros のうち1から9までの約数を取り出す
    divisors = [i for i in divisors if 1 <= i <= 9]

    for i in range(N + 2):
        flag = False
        for j in divisors:
            tmp = N // j
            if i % tmp == 0:
                print(j, end="")
                flag = True
                break
        if not flag:
            print("-", end="")


if __name__ == "__main__":
    solver()
