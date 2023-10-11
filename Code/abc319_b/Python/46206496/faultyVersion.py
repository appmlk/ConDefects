def getInt():
    return int(input())

def main():
    n = getInt()
    a = [j for j in range(1, 9) if n % j == 0]

    r = ''
    for i in range(n + 1):
        x = [b for b in a if i % (n / b) == 0]
        if len(x) == 0:
            r += '-'
        else:
            r += str(min(x))
    print(r)

if __name__ == "__main__":
    main()