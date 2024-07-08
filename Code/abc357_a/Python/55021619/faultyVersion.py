def main():
    n, m = map(int,input().split())
    h = list(map(int,input().split()))

    hand = 0
    for i in range(len(h)):
        hand = hand + h[i]

        if hand > m:
            print(i)
            break

        elif hand == m:
            print(i+1)
            break

if __name__ == "__main__":
    main()