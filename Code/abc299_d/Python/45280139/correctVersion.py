def main():
    N = int(input())

    l = [0,0]
    r = [N-1,1]

    for i in range(20):
        m = (r[0]+l[0])//2
        print(f"? {m+1}")
        N = int(input())

        if N == l[1]:
            l[0] = m
        elif N == r[1]:
            r[0] = m

        if abs(r[0]-l[0]) <= 1:
            break
    
    print(f"! {l[0]+1}")
            
if __name__ == "__main__":
    main()