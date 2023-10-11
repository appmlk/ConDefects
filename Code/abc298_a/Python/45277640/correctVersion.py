def main():
    n = int(input())
    s = list(input())

    ok = False

    for i in range(n):
        if s[i] == "o":
            ok = True
        elif s[i] == "x":
            print("No")
            return

    if ok == True:
        print("Yes")
    else:
        print("No")
    
if __name__ == "__main__":
    main()