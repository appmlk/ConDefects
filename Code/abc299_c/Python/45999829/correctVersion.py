def main():
    n = int(input())
    s = list(input())

    if n == 1:
        print(-1)
        return
    
    dango = 0
    max_dango = -1
    stick = False

    for i in range(n):
        if s[i] == "o":
            dango += 1
        else:
            stick = True
            if dango > max_dango:
                max_dango = dango
            dango = 0
    
    if dango > max_dango:
        max_dango = dango


    if stick == True and max_dango != 0:
        print(max_dango)
    else:
        print(-1)

if __name__ == "__main__":
    main()