## https://atcoder.jp/contests/arc166/tasks/arc166_b

def calc_gcd(A, B):
    """
    正の整数A, Bの最大公約数を計算する
    """
    a = max(A, B)
    b = min(A, B)
    while a % b > 0:
        c = a % b
        a = b
        b = c
    return b

def main():
    N, a, b, c = map(int, input().split())
    A = list(map(int, input().split()))

    ab = calc_gcd(a, b)
    ab = (a // ab) * b
    ac = calc_gcd(a, c)
    ac = (a // ac) * c
    bc = calc_gcd(b, c)
    bc = (b // bc) * c
    abc = calc_gcd(ab, c)
    abc = (ab // abc) * c
    array = [[0] * 9 for _ in range(N)]
    for i in range(N):
        array[i][0] = (-A[i] % a)
        array[i][1] = (-A[i] % b)
        array[i][2] = (-A[i] % c)
        array[i][3] = (-A[i] % ab)
        array[i][4] = (-A[i] % ac)
        array[i][5] = (-A[i] % bc)
        array[i][6] = (-A[i] % abc)
        array[i][7] = i
    
    answer = float("inf")
    # a, b, cそれぞれ1つずつある状態に持っていく場合
    # aにすると一番近いベスト3を選ぶ
    array_a = []
    array_b = []
    array_c = []
    array.sort(key=lambda x: x[0])
    for j in range(min(len(array), 3)):
        array_a.append((array[j][0], array[j][7]))
    array.sort(key=lambda x: x[1])
    for j in range(min(len(array), 3)):
        array_b.append((array[j][1], array[j][7]))
    array.sort(key=lambda x: x[2])
    for j in range(min(len(array), 3)):
        array_c.append((array[j][2], array[j][7]))
        
    if N >= 3:
        for i in range(3):
            for j in range(3):
                for k in range(3):
                    if array_a[i][1] != array_b[j][1] and array_b[j][1] != array_c[k][1] and array_a[i][1] != array_c[k][1]:
                        answer = min(answer, array_a[i][0] + array_b[j][0] + array_c[k][0])
    
    # ab, cそれぞれ1つずつある状態に持っていく場合
    if N >= 2:
        array_ab = []
        array.sort(key=lambda x: x[3])
        for j in range(2):
            array_ab.append((array[j][3], array[j][7]))
        array_ac = []
        array.sort(key=lambda x: x[4])
        for j in range(2):
            array_ac.append((array[j][4], array[j][7]))
        array_bc = []
        array.sort(key=lambda x: x[5])
        for j in range(2):
            array_bc.append((array[j][5], array[j][7]))
    
        # ab, c
        for i in range(2):
            for j in range(2):
                if array_ab[i][1] != array_c[j][1]:
                    answer = min(answer, array_ab[i][0] + array_c[j][0])
        # ac, b
        for i in range(2):
            for j in range(2):
                if array_ac[i][1] != array_b[j][1]:
                    answer = min(answer, array_ac[i][0] + array_b[j][0])
        # bc, a
        for i in range(2):
            for j in range(2):
                if array_bc[i][1] != array_a[j][1]:
                    answer = min(answer, array_bc[i][0] + array_a[j][0])
    
    #abcの場合
    for j in range(N):
        if array[j][6] < answer:
            answer = array[j][6]
    
    print(answer)

           
            







   
if __name__ == "__main__":
    main()
