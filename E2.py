def vacuum_world():
    print("Name : Soumodeep Dutta , TE-CMPN A")
    print("Dirt Sucking Cost : 10 , Moving Cost : 1")

    location = input("Start Tile  (A/B): ").upper()
    A = input("Is A Dirty? (1=Yes, 0=No): ")
    B = input("Is B Dirty? (1=Yes, 0=No): ")

    cost = 0

    while A == '1' or B == '1':
        print("At", location, "| State:", {'A': A, 'B': B})

        if location == 'A':
            if A == '1':
                A = '0'
                print("  Action: SUCK (Cleaned A)")
                cost += 10
            else:
                location = 'B'
                cost += 1
                print("  Action: RIGHT")

        else: 
            if B == '1':
                B = '0'
                print("  Action: SUCK (Cleaned B)")
                cost += 10
            else:
                location = 'A'
                cost += 1
                print("  Action: LEFT")

    print("\nResult: All Clean! \nTotal Cost:", cost)


if __name__ == "__main__":
    vacuum_world()
