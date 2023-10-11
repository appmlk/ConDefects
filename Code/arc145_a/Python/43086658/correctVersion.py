# ARC145 A - AB Palindrome
# https://atcoder.jp/contests/arc145/tasks/arc145_a

def main():
    N = int(input())
    S = input()

    ans = "Yes" if solve(N, S) is True else "No"
    print(ans)


def solve(N: int, S: str) -> bool:
    if S == "AA" or S == "BB":
        return True

    if S == "AB" or S == "BA":
        return False

    # 先頭文字が「A」、末尾文字が「B」のとき回文不可能
    if S[0] == "A" and S[-1] == "B":
        return False
    
    # 先頭文字が「B」
    if S[0] == "B":
        return True
    
    # 末尾文字が「A」
    if S[-1] == "A":
        return True
    

if __name__ == "__main__":
    main()
