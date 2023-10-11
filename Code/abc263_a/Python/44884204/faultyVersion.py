def main():
    cards = list(map(int, input().split()))
    assert len(cards) == 5

    cards.sort()
    if cards[0] == cards[1] and cards[0] == cards[2] and cards[0] != cards[3] and cards[3] == cards[4]:
        print("Yes")
    else:
        print("No")


if __name__ == "__main__":
    main()
