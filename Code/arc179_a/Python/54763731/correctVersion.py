def main():
    N,K=map(int,input().split())
    A=list(map(int,input().split()))
    if K>=1:
        print("Yes")
        A.sort()
        print(*A)
    elif sum(A)>=K:
        print("Yes")
        A.sort(reverse=True)
        print(*A)
    else:
        print("No")



if __name__=="__main__":
    main()