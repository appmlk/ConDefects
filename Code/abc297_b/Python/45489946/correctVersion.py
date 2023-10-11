def main():
    s = input()
    slist = list(s)

    if not "K" in s.split("R")[1]:
        print("No")
        return

    firstB = s.index("B")
    secondB = s.rindex("B")

    if (secondB-firstB) % 2 == 0:
        print("No")
        return
    
    print("Yes")

if __name__ == "__main__":
    main()