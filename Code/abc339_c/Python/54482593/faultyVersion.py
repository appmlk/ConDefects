# Perfect Bus
def calc(N, A):
    start = 0
    total = 0
    for i in A:
        total += i
        if -start > total:
            start = -total
        print("total: " + str(total))
        print("start: " + str(start))
    return start + total

def main():
    N = int(input())
    A = [int(i) for i in input().split()]
    print(calc(N, A))

if __name__=="__main__":
    main()
