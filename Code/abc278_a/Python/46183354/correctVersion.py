def main():
    N, K = map(int, input().split())
    A = list(map(int, input().split()))
    if N <= K:
        print(*([0] * N))
    else:
        print(*(A[K:] + [0] * K))


if __name__ == "__main__":
    main()
